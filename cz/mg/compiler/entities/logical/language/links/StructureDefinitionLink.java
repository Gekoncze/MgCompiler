package cz.mg.compiler.entities.logical.language.links;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.definitions.StructureDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class StructureDefinitionLink extends DatatypeDefinitionLink<StructureDefinition> {
    public StructureDefinitionLink(Context context, Text name) {
        super(context, name);
    }
}
