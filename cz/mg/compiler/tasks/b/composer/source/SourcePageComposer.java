package cz.mg.compiler.tasks.b.composer.source;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.b.logical.source.LogicalSourcePage;
import cz.mg.compiler.entities.a.segments.Page;
import cz.mg.compiler.entities.b.logical.source.LogicalClass;
import cz.mg.compiler.entities.b.logical.source.LogicalModule;
import cz.mg.compiler.entities.b.logical.source.LogicalOperator;
import cz.mg.compiler.entities.b.logical.source.LogicalUsing;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.tasks.b.composer.Composer;


public class SourcePageComposer extends Composer<Task, Composer, Page, LogicalSourcePage> {
    public SourcePageComposer(Task parentTask, Page input, LogicalSourcePage output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader reader) {
        throw new RuntimeException();
    }

    @Override
    protected void onLinesRead(ChainList<Line> lines) {
        LogicalSourcePage logicalFile = getOutput();
        
        for(Line line : lines){
            if(Rules.MODULE.applicable(line)){
                ModuleComposer moduleComposer = new ModuleComposer(this, line, new LogicalModule(logicalFile, line.getLocation()));
                moduleComposer.run();
            } else if(Rules.USING.applicable(line)){
                UsingComposer usingComposer = new UsingComposer(this, line, new LogicalUsing(logicalFile, line.getLocation()));
                usingComposer.run();
            } else if(Rules.CLASS.applicable(line)){
                ClassComposer classComposer = new ClassComposer(this, line, new LogicalClass(logicalFile, line.getLocation()));
                classComposer.run();
            } else if(Rules.OPERATOR.applicable(line)){
                OperatorComposer operatorComposer = new OperatorComposer(this, line, new LogicalOperator(logicalFile, line.getLocation()));
                operatorComposer.run();
            } else {
                throw new CompileException(line, "No rule found for given line.");
            }
        }
    }
}
