package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.usings.UsingAll;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.Task;


public class BuildUsingAllTask extends BuildUsingTask {
    private UsingAll using = null;

    public BuildUsingAllTask(Task parentTask, Block block, Context context) {
        super(parentTask, block, context);
    }

    public UsingAll getUsing() {
        return using;
    }

    @Override
    protected void build(Block block) {
        using = new UsingAll(block.getTrace(), buildPath(block.getParts().get(2)));
    }
}
