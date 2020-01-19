package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.commands.BlockCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.builder.language.BuildCommandContainerTask;


public abstract class BuildBlockCommandTask extends BuildCommandContainerTask {
    @Link
    protected BlockCommand command = null;

    public BuildBlockCommandTask(Block block, Context context) {
        super(block, context);
    }

    public BlockCommand getCommand() {
        return command;
    }

    @Override
    protected Object getParent() {
        return command;
    }
}
