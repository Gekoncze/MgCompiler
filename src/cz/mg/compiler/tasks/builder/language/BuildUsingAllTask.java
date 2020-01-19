package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.usings.UsingAll;
import cz.mg.compiler.entities.structured.Block;


public class BuildUsingAllTask extends BuildUsingTask {
    @Link
    private UsingAll using = null;

    public BuildUsingAllTask(Block block, Context context) {
        super(block, context);
    }

    public UsingAll getUsing() {
        return using;
    }

    @Override
    protected void build(Block block) {
        using = new UsingAll(block.getTrace(), buildPath(block.getParts().get(2)));
    }
}
