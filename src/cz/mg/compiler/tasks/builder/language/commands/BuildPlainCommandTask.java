package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.commands.PlainCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.language.BuildCallTask;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.*;


public class BuildPlainCommandTask extends BuildCommandTask {
    public BuildPlainCommandTask(Task parentTask, Block block, Context context) {
        super(parentTask, block, context);
    }

    @Override
    protected void build(Block block) {
        BuildCallTask task = new BuildCallTask(this, block.getParts().get(0), getContext());
        task.tryToRun();
        command = new PlainCommand(block.getTrace());
        command.setCall(task.getCall());
        store(this, "declaredVariables", task.getDeclaredVariables());
    }
}
