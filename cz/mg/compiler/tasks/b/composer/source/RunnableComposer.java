package cz.mg.compiler.tasks.b.composer.source;

import cz.mg.compiler.entities.b.logical.source.LogicalVariable;
import cz.mg.compiler.entities.b.logical.source.LogicalVariableGroup;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.tokens.NameToken;
import cz.mg.compiler.entities.a.segments.tokens.TypeToken;
import cz.mg.compiler.entities.b.logical.Logical;
import cz.mg.compiler.entities.b.logical.source.LogicalType;
import cz.mg.compiler.tasks.b.composer.Composer;
import cz.mg.compiler.tasks.b.composer.source.command.CommandComposer;


public abstract class RunnableComposer extends CommandComposer {
    public RunnableComposer(Composer parentTask, Line input, Logical output) {
        super(parentTask, input, output);
    }
    
    protected static void readVariable(LogicalVariableGroup parent, TokenReader reader) {
        TypeToken typeToken = (TypeToken) reader.moveNext(TypeToken.class);
        NameToken nameToken = (NameToken) reader.moveNext(NameToken.class);
        
        LogicalVariable variable = new LogicalVariable(parent, nameToken.getLocation());
        variable.setName(nameToken.getText());
        new LogicalType(variable, typeToken.getLocation(), typeToken.getText(), typeToken.getPointerCount());
    }
    
    protected static void readOutputVariable(LogicalVariableGroup parent, TokenReader reader) {
        TypeToken typeToken = (TypeToken) reader.moveNext(TypeToken.class);
        LogicalVariable variable = new LogicalVariable(parent, typeToken.getLocation());
        new LogicalType(variable, typeToken.getLocation(), typeToken.getText(), typeToken.getPointerCount());
    }
}
