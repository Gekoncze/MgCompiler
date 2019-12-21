package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.commands.ReturnCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Expression;
import cz.mg.compiler.tasks.builder.language.BuildCallTask;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildReturnCommandTask extends BuildCommandTask {
    private final boolean expression;

    public BuildReturnCommandTask(Block block, Context context, boolean expression) {
        super(block, context);
        this.expression = expression;
    }

    @Override
    protected void build(Block block) {
        if(expression){
            Expression expression = cast(block.getParts().get(1), Expression.class);
            BuildCallTask task = new BuildCallTask(expression, getContext());
            task.tryToRun();
            command = new ReturnCommand(block.getTrace());
            command.setCall(task.getCall());
        } else {
            command = new ReturnCommand(block.getTrace());
        }
    }
}
