package cz.mg.compiler.tasks.builder.language.natives;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.natives.NativeStructureDefinition;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildNativeStructureDefinitionTask extends BuildNativeDatatypeDefinitionTask {
    private NativeStructureDefinition structureDefinition;

    public BuildNativeStructureDefinitionTask(Task parentTask, Block block, Context context) {
        super(parentTask, block, context);
    }

    @Override
    public NativeStructureDefinition getDefinition() {
        return structureDefinition;
    }

    @Override
    public Rules getRules() {
        return rulesLeft;
    }

    @Override
    protected void build(Block block) {
        Name name = cast(block.getParts().get(2), Name.class);
        structureDefinition = new NativeStructureDefinition(name.getContent());
        structureDefinition.setStamps(takeStamps());
        structureDefinition.setDocumentation(takeDocumentation());
    }
}
