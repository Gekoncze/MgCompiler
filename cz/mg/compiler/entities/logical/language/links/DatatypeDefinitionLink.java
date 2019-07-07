package cz.mg.compiler.entities.logical.language.links;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.definitions.DatatypeDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class DatatypeDefinitionLink<T extends DatatypeDefinition> extends NamedLink<T> {
    public DatatypeDefinitionLink(Context context, Text name) {
        super(context, name);
    }
}
