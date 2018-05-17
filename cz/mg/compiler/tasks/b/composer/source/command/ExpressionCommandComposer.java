package cz.mg.compiler.tasks.b.composer.source.command;

import cz.mg.collections.chainlist.ChainList;
import cz.mg.collections.chainlist.ChainListItem;
import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.a.segments.tokens.ClosingToken;
import cz.mg.compiler.entities.a.segments.tokens.LiteralToken;
import cz.mg.compiler.entities.a.segments.tokens.NameToken;
import cz.mg.compiler.entities.a.segments.tokens.OpeningToken;
import cz.mg.compiler.entities.a.segments.tokens.OperatorToken;
import cz.mg.compiler.entities.a.segments.tokens.Token;
import cz.mg.compiler.entities.a.segments.tokens.TypeToken;
import cz.mg.compiler.entities.b.logical.Logical;
import cz.mg.compiler.entities.b.logical.source.LogicalType;
import cz.mg.compiler.entities.b.logical.source.LogicalVariable;
import cz.mg.compiler.entities.b.logical.source.calls.LogicalAssignmentCall;
import cz.mg.compiler.entities.b.logical.source.calls.LogicalCall;
import cz.mg.compiler.entities.b.logical.source.calls.LogicalGroupCall;
import cz.mg.compiler.entities.b.logical.source.calls.LogicalLiteralCall;
import cz.mg.compiler.entities.b.logical.source.calls.LogicalMemberAccessCall;
import cz.mg.compiler.entities.b.logical.source.calls.LogicalNameCall;
import cz.mg.compiler.entities.b.logical.source.calls.LogicalOperatorCall;
import cz.mg.compiler.entities.b.logical.source.commands.LogicalExpressionCommand;
import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.tasks.b.composer.Composer;
import cz.mg.compiler.tasks.b.composer.source.TokenReader;


public class ExpressionCommandComposer extends CommandComposer {
    public ExpressionCommandComposer(Composer parentTask, Line input, Logical output) {
        super(parentTask, input, output);
    }
    
    @Override
    protected void onTokensRead(TokenReader reader) {
        LogicalCall rootCall = buildTree(buildChain(reader, null));
        if(rootCall != null) rootCall.setParent(getOutput());
    }

    @Override
    protected void onLinesRead(ChainList<Line> lines) {
        for(Line line : lines){
            ExpressionCommandComposer expressionComposer = new ExpressionCommandComposer(this, line, new LogicalExpressionCommand(getOutput(), line.getLocation()));
            expressionComposer.run();
        }
    }
        
    private LogicalCall buildTree(ChainList<Token> parts) {
		if(parts.size() <= 0) return null; // empty expression
		
		buildValueCalls(parts);
		buildAllOperatorCalls(parts);
        
        if(parts.size() != 1) throw new CompileException(getInput(), "Invalid expression. Remaining tokens: ", parts.toString(", "));
        if(parts.getFirst() instanceof ExpressionToken){
			return ((ExpressionToken)parts.getFirst()).getCall();
		} else {
			throw new CompileException((Word)parts.getFirst(), "Invalid expression near '" + parts.getFirst().getText() + "'.");
		}
    }
	
	private void buildValueCalls(ChainList<Token> parts){
		for(ChainListItem<Token> item = parts.getFirstItem(); item != null; item = item.getNextItem()){
			if(item.getPrevious() instanceof TypeToken) item.removePrevious();
			Token token = item.getData();
			if(token instanceof TypeToken) {
				if(!(item.getNext() instanceof NameToken)) throw new CompileException((Word)token, "Missing variable name for declaration.");
				TypeToken typeToken = (TypeToken) token;
				NameToken nameToken = (NameToken) item.getNext();
				LogicalVariable variable = new LogicalVariable(getOutput(), nameToken.getLocation());
                variable.setName(nameToken.getText());
                LogicalType type = new LogicalType(variable, typeToken.getLocation(), typeToken.getText(), typeToken.getPointerCount());
			}
			if(token instanceof NameToken) {
				item.setData(new ExpressionToken(new LogicalNameCall(token.getLocation(), token.getText())));
			}
			if(token instanceof LiteralToken) {
				item.setData(new ExpressionToken(new LogicalLiteralCall(token.getLocation(), token.getText())));
			}
		}
	}
	
	private void buildAllOperatorCalls(ChainList<Token> parts){
		for(OperatorToken.Operator op : OperatorToken.Operator.values()){
			switch(op.searchOrder()){
				case LEFT_TO_RIGHT: buildOperatorCallsLeftToRight(parts, op); break;
				case RIGHT_TO_LEFT: buildOperatorCallsRightToLeft(parts, op); break;
				default: throw new RuntimeException();
			}
            if(parts.size() == 1) break;
        }
	}
	
	private void buildOperatorCallsLeftToRight(ChainList<Token> parts, OperatorToken.Operator op){
		for(ChainListItem<Token> item = parts.getFirstItem(); item != null; item = item.getNextItem()){
			buildOperatorCall(item, op);
		}
	}
	
	private void buildOperatorCallsRightToLeft(ChainList<Token> parts, OperatorToken.Operator op){
		for(ChainListItem<Token> item = parts.getLastItem(); item != null; item = item.getPreviousItem()){
			buildOperatorCall(item, op);
		}
	}
	
	private void buildOperatorCall(ChainListItem<Token> item, OperatorToken.Operator op){
		Token token = item.getData();
		if(token instanceof OperatorToken) {
			OperatorToken operatorToken = (OperatorToken) token;
			if(op != operatorToken.getOperator()) return;
			switch(operatorToken.getOperator()){
				case ASSIGNMENT: buildAssignmentCall(operatorToken, item); break;
				case DOT: buildMemberAccessCall(operatorToken, item); break;
				case COMMA: buildGroupCall(operatorToken, item); break;
				case COLON: buildFunctionCall(operatorToken, item); break;
				default: buildRegularOperatorCall(operatorToken, item); break;
			}
		}
	}
	
	private void buildAssignmentCall(OperatorToken operator, ChainListItem<Token> item){
		LogicalCall previous = removePreviousExpression(operator, item);
		LogicalCall next = removeNextExpression(operator, item);
		if(previous instanceof LogicalLiteralCall) throw new CompileException((Word)operator, "Cannot assign value to literal.");
        LogicalAssignmentCall call = new LogicalAssignmentCall(operator.getLocation());
        previous.setParent(call);
        next.setParent(call);
		item.setData(new ExpressionToken(call));
	}
	
	private void buildMemberAccessCall(OperatorToken operator, ChainListItem<Token> item){
		LogicalCall previous = removePreviousExpression(operator, item);
		LogicalCall next = removeNextExpression(operator, item);
		if(previous instanceof LogicalLiteralCall) throw new CompileException((Word)operator, "Literal cannot have members.");
		if(next instanceof LogicalLiteralCall) throw new CompileException((Word)operator, "Literal cannot be member.");
		if(previous instanceof LogicalGroupCall) throw new CompileException((Word)operator, "Cannot access members of a group.");
		if(next instanceof LogicalGroupCall) throw new CompileException((Word)operator, "Group cannot be member;.");
        LogicalMemberAccessCall call = new LogicalMemberAccessCall(operator.getLocation());
        previous.setParent(call);
        next.setParent(call);
		item.setData(new ExpressionToken(call));
	}
	
	private void buildRegularOperatorCall(OperatorToken operator, ChainListItem<Token> item){
		LogicalCall previous = null;
		LogicalCall next = null;
		switch(operator.getOperator().position()){
			case PREFIX:
				next = removeNextExpression(operator, item);
				break;
			case INFIX:
				previous = removePreviousExpression(operator, item);
				next = removeNextExpression(operator, item);
				break;
			case POSTFIX:
				previous = removePreviousExpression(operator, item);
				break;
		}
		if(previous instanceof LogicalGroupCall) throw new CompileException((Word)operator, "Operators does not accept group as parameter. (left)");
		if(next instanceof LogicalGroupCall) throw new CompileException((Word)operator, "Operators does not accept group as parameter. (right)");
        LogicalOperatorCall call = new LogicalOperatorCall(operator.getLocation(), operator.getOperator(), operator.getText());
        if(previous != null) previous.setParent(call);
        if(next != null) next.setParent(call);
		item.setData(new ExpressionToken(call));
	}
	
	private LogicalCall removePreviousExpression(OperatorToken operator, ChainListItem<Token> item){
		if(!(item.getPrevious() instanceof ExpressionToken)) throw new CompileException((Word)operator, "Invalid expression.");
		return ((ExpressionToken) item.removePrevious()).getCall();
	}
	
	private LogicalCall removeNextExpression(OperatorToken operator, ChainListItem<Token> item){
		if(!(item.getNext() instanceof ExpressionToken)) throw new CompileException((Word)operator, "Invalid expression.");
		return ((ExpressionToken) item.removeNext()).getCall();
	}
	
	private void buildFunctionCall(OperatorToken operator, ChainListItem<Token> item){
		LogicalCall previous = removePreviousExpression(operator, item);
		LogicalCall next = removeNextExpression(operator, item);
		if(!(previous instanceof LogicalNameCall)) throw new CompileException((Word)operator, "Left operand of colon operator must be identifier.");
        next.setParent(previous);
		item.setData(new ExpressionToken(previous));
	}
	
	private LogicalGroupCall buildGroupCall(OperatorToken operator, ChainListItem<Token> item){
		LogicalCall previous = removePreviousExpression(operator, item);
		LogicalCall next = removeNextExpression(operator, item);
        LogicalGroupCall group = new LogicalGroupCall(operator.getLocation());
        previous.setParent(group);
        next.setParent(group);
		item.setData(new ExpressionToken(group));
        return group;
	}
	
	private ChainList<Token> buildChain(TokenReader reader, OpeningToken matchingOpeningToken) {
        ChainList<Token> current = new ChainList();
        while(reader.hasNext()){
            Token token = reader.moveNext();
			if(token instanceof OpeningToken){
                LogicalCall call = buildTree(buildChain(reader, (OpeningToken) token));
				if(call != null) current.addLast(new ExpressionToken(call));
			} else if(token instanceof ClosingToken){
                if(matchingOpeningToken == null) throw new CompileException((Word)token, "Missing left bracket.");
                return current;
            } else {
				current.addLast(token);
			}
        }
        if(matchingOpeningToken != null) throw new CompileException((Word)matchingOpeningToken, "Missing right bracket.");
		if(current.isEmpty() && matchingOpeningToken != null) throw new CompileException(matchingOpeningToken, "Unexpected empty expression.");
        return current;
    }
	
	private static class ExpressionToken implements Token {
		private final LogicalCall call;

		public ExpressionToken(LogicalCall call) {
			this.call = call;
		}

		public LogicalCall getCall() {
			return call;
		}

        @Override
        public String getText() {
            throw new RuntimeException();
        }

        @Override
        public Location getLocation() {
            throw new RuntimeException();
        }
	}
}
