package cz.mg.compiler.tasks.b.composer.source;

import cz.mg.compiler.entities.b.logical.source.LogicalFunction;
import cz.mg.compiler.entities.b.logical.source.LogicalVariableGroup;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import static cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword.*;
import cz.mg.compiler.entities.a.segments.tokens.NameToken;
import cz.mg.compiler.entities.a.segments.tokens.OperatorToken.Operator;
import cz.mg.compiler.entities.a.segments.tokens.Token;
import cz.mg.compiler.entities.b.logical.source.calls.LogicalAccessUsage;


public class FunctionComposer extends RunnableComposer {
    public FunctionComposer(ClassComposer parentTask, Line input, LogicalFunction output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader reader) {
		LogicalFunction logicalFunction = (LogicalFunction) getOutput();
        
        reader.moveNext(DEFINE);
        
        Token accessToken = reader.moveNext(NameToken.class, KeywordToken.class);
		new LogicalAccessUsage(logicalFunction, accessToken.getLocation(), accessToken.getText());
        
        reader.moveNext(FUNCTION);
        
        NameToken nameToken = (NameToken) reader.moveNext(NameToken.class);
        logicalFunction.setName(nameToken.getText());
		
		if(reader.moveNextOptional(INPUT) != null){
            LogicalVariableGroup input = new LogicalVariableGroup(logicalFunction, getInput().getLocation(), "INPUT");
            RunnableComposer.readVariable(input, reader);
            while(reader.moveNextOptional(Operator.COMMA) != null){
                RunnableComposer.readVariable(input, reader);
            }
        }
        
        if(reader.moveNextOptional(OUTPUT) != null){
            LogicalVariableGroup output = new LogicalVariableGroup(logicalFunction, getInput().getLocation(), "OUTPUT");
            RunnableComposer.readOutputVariable(output, reader);
            while(reader.moveNextOptional(Operator.COMMA) != null){
                RunnableComposer.readOutputVariable(output, reader);
            }
        }
        
        reader.readEnd(logicalFunction);
    }
}
