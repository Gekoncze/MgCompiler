package cz.mg.compiler.tasks.b.composer.source;

import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.entities.b.logical.source.LogicalOperator;
import cz.mg.compiler.entities.b.logical.source.LogicalVariableGroup;
import static cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword.*;
import cz.mg.compiler.entities.a.segments.tokens.OperatorToken.Operator;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import cz.mg.compiler.entities.a.segments.tokens.NameToken;
import cz.mg.compiler.entities.a.segments.tokens.OperatorToken;
import cz.mg.compiler.entities.a.segments.tokens.Token;
import cz.mg.compiler.entities.b.logical.Logical;
import cz.mg.compiler.entities.b.logical.source.calls.LogicalAccessUsage;
import cz.mg.compiler.tasks.b.composer.Composer;


public class OperatorComposer extends RunnableComposer {
    public OperatorComposer(Composer parentTask, Line input, Logical output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader reader) {
		LogicalOperator logicalOperator = (LogicalOperator) getOutput();
        
        reader.moveNext(DEFINE);
        
        Token accessToken = reader.moveNext(NameToken.class, KeywordToken.class);
		new LogicalAccessUsage(logicalOperator, accessToken.getLocation(), accessToken.getText());
        
        reader.moveNext(OPERATOR);
        
        OperatorToken operatorToken = (OperatorToken) reader.moveNext(OperatorToken.class);
        logicalOperator.setName(operatorToken.getText());
        logicalOperator.setOperator(operatorToken.getOperator());
		
		if(reader.moveNextOptional(INPUT) != null){
            LogicalVariableGroup left = new LogicalVariableGroup(logicalOperator, getInput().getLocation(), "LEFT");
			RunnableComposer.readVariable(left, reader);
            if(reader.moveNextOptional(Operator.COMMA) != null){
                LogicalVariableGroup right = new LogicalVariableGroup(logicalOperator, getInput().getLocation(), "RIGHT");
				RunnableComposer.readVariable(right, reader);
                if(reader.moveNextOptional(Operator.COMMA) != null) throw new CompileException((Word)reader.read(), "Operator cannot have more than two parameters.");
            }
        }
		
        if(reader.moveNextOptional(OUTPUT) != null){
            LogicalVariableGroup output = new LogicalVariableGroup(logicalOperator, getInput().getLocation(), "OUTPUT");
			RunnableComposer.readOutputVariable(output, reader);
            if(reader.moveNextOptional(Operator.COMMA) != null){
                throw new CompileException((Word)reader.read(), "Operator cannot have more than one return value.");
            }
        }
        
        reader.readEnd(logicalOperator);
    }
}
