package cz.mg.compiler.tasks.builder.language.natives;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.natives.NativeUsing;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Value;
import cz.mg.compiler.tasks.builder.language.BuildUsingTask;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildNativeUsingTask extends BuildUsingTask {
    @Link
    private NativeUsing using = null;

    public BuildNativeUsingTask(Block block, Context context) {
        super(block, context);
    }

    public NativeUsing getUsing() {
        return using;
    }

    @Override
    protected void build(Block block) {
        Value value = cast(block.getParts().get(2), Value.class);
        using = new NativeUsing(block.getTrace(), value.getValue());
    }
}
