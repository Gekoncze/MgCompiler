package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.usings.UsingAll;
import cz.mg.compiler.entities.structured.Block;


public class BuildUsingAllTask extends BuildUsingTask {
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
