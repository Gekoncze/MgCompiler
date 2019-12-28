package cz.mg.compiler.tasks.builder.language.natives;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.natives.NativeTypeDefinition;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.*;


public class BuildNativeTypeDefinitionTask extends BuildNativeDatatypeDefinitionTask {
    @Link
    private NativeTypeDefinition typeDefinition;

    public BuildNativeTypeDefinitionTask(Block block, Context context) {
        super(block, context);
    }

    @Override
    public NativeTypeDefinition getDefinition() {
        return typeDefinition;
    }

    @Override
    public Rules getRules() {
        return rulesRight;
    }

    @Override
    protected void build(Block block) {
        Name name = cast(block.getParts().get(2), Name.class);
        typeDefinition = new NativeTypeDefinition(name.getContent());
        typeDefinition.setStamps(takeStamps());
        typeDefinition.setDocumentation(takeDocumentation());
    }
}
