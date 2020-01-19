package cz.mg.compiler.entities.logical.mg.links;

import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.definitions.DatatypeDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class DatatypeDefinitionLink<T extends DatatypeDefinition> extends NamedLink<T> {
    public DatatypeDefinitionLink(Context context, Text name) {
        super(context, name);
    }
}
