package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.definitions.ClassDefinition;
import cz.mg.compiler.entities.logical.mg.links.ClassDefinitionLink;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.entities.structured.parts.chains.List;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.entities.logical.mg.definitions.DatatypeDefinition.Inheritance;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.*;


public class BuildClassDefinitionTask extends BuildDatatypeDefinitionTask {
    @Link
    private final Inheritance inheritance;

    @Link
    private ClassDefinition classDefinition;

    public BuildClassDefinitionTask(Block block, Context context, Inheritance inheritance) {
        super(block, context);
        this.inheritance = inheritance;
    }

    @Override
    public ClassDefinition getDefinition() {
        return classDefinition;
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
        classDefinition = new ClassDefinition(name.getContent(), inheritance);
        if(inheritance != null){
            List bases = toList(block.getParts().get(3));
            for(Part base : bases.getParts()){
                Name baseName = cast(base, Name.class);
                classDefinition.getBases().addLast(new ClassDefinitionLink(getContext(), baseName.getContent()));
            }
        }
        classDefinition.setStamps(takeStamps());
        classDefinition.setDocumentation(takeDocumentation());
    }
}
