package cz.mg.compiler.entities.logical.mg.links;

import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.definitions.StructureDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class StructureDefinitionLink extends DatatypeDefinitionLink<StructureDefinition> {
    public StructureDefinitionLink(Context context, Text name) {
        super(context, name);
    }
}
