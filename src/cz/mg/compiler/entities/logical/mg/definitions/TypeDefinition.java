package cz.mg.compiler.entities.logical.mg.definitions;

import cz.mg.compiler.utilities.debug.Text;


public class TypeDefinition extends DatatypeDefinition {
    public TypeDefinition(Text name, Inheritance inheritance) {
        super(name, inheritance);
    }

    public TypeDefinition(Text name) {
        super(name, null);
    }
}
