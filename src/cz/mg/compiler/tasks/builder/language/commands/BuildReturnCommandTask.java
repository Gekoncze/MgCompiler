package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.commands.ReturnCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Expression;
import cz.mg.compiler.tasks.builder.language.BuildCallTask;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildReturnCommandTask extends BuildCommandTask {
    @Info
    private final boolean expression;

    public BuildReturnCommandTask(Block block, Context context, boolean expression) {
        super(block, context);
        this.expression = expression;
    }

    @Part
    private BuildCallTask buildCallTask;

    @Override
    protected void build(Block block) {
        if(expression){
            Expression expression = cast(block.getParts().get(1), Expression.class);
            buildCallTask = new BuildCallTask(expression, getContext());
            buildCallTask.tryToRun();
            command = new ReturnCommand(block.getTrace());
            command.setCall(buildCallTask.getCall());
        } else {
            command = new ReturnCommand(block.getTrace());
        }
    }
}
