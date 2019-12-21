package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.usings.UsingDirect;
import cz.mg.compiler.entities.structured.Block;


public class BuildUsingDirectTask extends BuildUsingTask {
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
