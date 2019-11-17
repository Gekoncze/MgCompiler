package cz.mg.compiler.entities.logical.language.links;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.definitions.TypeDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class TypeDefinitionLink extends DatatypeDefinitionLink<TypeDefinition> {
    public TypeDefinitionLink(Context context, Text name) {
        super(context, name);
    }
}
