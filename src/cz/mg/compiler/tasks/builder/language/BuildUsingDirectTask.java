package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.usings.UsingDirect;
import cz.mg.compiler.entities.structured.Block;


public class BuildUsingDirectTask extends BuildUsingTask {
    @Link
    private UsingDirect using = null;

    public BuildUsingDirectTask(Block block, Context context) {
        super(block, context);
    }

    public UsingDirect getUsing() {
        return using;
    }

    @Override
    protected void build(Block block) {
        using = new UsingDirect(
                block.getTrace(),
                buildPath(block.getParts().get(1))
        );
    }
}
