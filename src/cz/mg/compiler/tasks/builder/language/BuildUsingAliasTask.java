package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.usings.UsingAlias;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.utilities.debug.Text;

import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildUsingAliasTask extends BuildUsingTask {
    @Link
    private UsingAlias using = null;

    public BuildUsingAliasTask(Block block, Context context) {
        super(block, context);
    }

    public UsingAlias getUsing() {
        return using;
    }

    @Override
    protected void build(Block block) {
        using = new UsingAlias(
                block.getTrace(),
                buildPath(block.getParts().get(1)),
                buildAlias(block.getParts().get(3))
        );
    }

    private Text buildAlias(Part part){
        Name name = cast(part, Name.class);
        return name.getContent();
    }
}
