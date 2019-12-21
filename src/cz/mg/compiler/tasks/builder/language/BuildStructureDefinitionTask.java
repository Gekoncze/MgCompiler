package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.definitions.StructureDefinition;
import cz.mg.compiler.entities.logical.language.links.ClassDefinitionLink;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.entities.logical.language.definitions.DatatypeDefinition.Inheritance;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildStructureDefinitionTask extends BuildDatatypeDefinitionTask {
    private final Inheritance inheritance;
    private StructureDefinition structureDefinition;

    public BuildStructureDefinitionTask(Block block, Context context, Inheritance inheritance) {
        super(block, context);
        this.inheritance = inheritance;
    }

    @Override
    public StructureDefinition getDefinition() {
        return structureDefinition;
    }

    @Override
    public Rules getRules() {
        if(inheritance == null) return rulesLeft;
        switch (inheritance){
            case IS: return rulesLeft;
            case ALIAS: return rulesRight;
            case VIEW: return rulesRight;
            case LIKE: return rulesLeft;
            default: throw new RuntimeException();
        }
    }

    @Override
    protected void build(Block block) {
        Name name = cast(block.getParts().get(1), Name.class);
        structureDefinition = new StructureDefinition(name.getContent(), inheritance);
        if(inheritance != null){
            Name baseName = cast(block.getParts().get(3), Name.class);
            structureDefinition.getBases().addLast(new ClassDefinitionLink(getContext(), baseName.getContent()));
        }
        structureDefinition.setStamps(takeStamps());
        structureDefinition.setDocumentation(takeDocumentation());
    }
}
