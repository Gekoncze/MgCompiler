package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.definitions.TypeDefinition;
import cz.mg.compiler.entities.logical.language.links.ClassDefinitionLink;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.entities.logical.language.definitions.DatatypeDefinition.Inheritance;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildTypeDefinitionTask extends BuildDatatypeDefinitionTask {
    private final Inheritance inheritance;
    private TypeDefinition typeDefinition;

    public BuildTypeDefinitionTask(Block block, Context context, Inheritance inheritance) {
        super(block, context);
        this.inheritance = inheritance;
    }

    @Override
    public TypeDefinition getDefinition() {
        return typeDefinition;
    }

    @Override
    public Rules getRules() {
        return rulesRight;
    }

    @Override
    protected void build(Block block) {
        Name name = cast(block.getParts().get(1), Name.class);
        typeDefinition = new TypeDefinition(name.getContent(), inheritance);
        if(inheritance != null){
            Name baseName = cast(block.getParts().get(3), Name.class);
            typeDefinition.getBases().addLast(new ClassDefinitionLink(getContext(), baseName.getContent()));
        }
        typeDefinition.setStamps(takeStamps());
        typeDefinition.setDocumentation(takeDocumentation());
    }
}
