package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.commands.PlainCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.builder.language.BuildCallTask;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.*;


public class BuildPlainCommandTask extends BuildCommandTask {
    public BuildPlainCommandTask(Block block, Context context) {
        super(block, context);
    }

    @Part
    private BuildCallTask buildCallTask;

    @Override
    protected void build(Block block) {
        buildCallTask = new BuildCallTask(block.getParts().get(0), getContext());
        buildCallTask.tryToRun();
        command = new PlainCommand(block.getTrace());
        command.setCall(buildCallTask.getCall());
        store(this, "declaredVariables", buildCallTask.getDeclaredVariables());
    }
}
