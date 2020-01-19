package cz.mg.compiler.entities.logical.mg.links;

import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.definitions.ClassDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class ClassDefinitionLink extends DatatypeDefinitionLink<ClassDefinition> {
    public ClassDefinitionLink(Context context, Text name) {
        super(context, name);
    }
}
