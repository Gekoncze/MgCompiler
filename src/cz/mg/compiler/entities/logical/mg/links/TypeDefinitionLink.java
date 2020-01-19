package cz.mg.compiler.entities.logical.mg.links;

import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.definitions.TypeDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class TypeDefinitionLink extends DatatypeDefinitionLink<TypeDefinition> {
    public TypeDefinitionLink(Context context, Text name) {
        super(context, name);
    }
}
