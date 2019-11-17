package cz.mg.compiler.entities.logical.language.links;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.definitions.ClassDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class ClassDefinitionLink extends DatatypeDefinitionLink<ClassDefinition> {
    public ClassDefinitionLink(Context context, Text name) {
        super(context, name);
    }
}
