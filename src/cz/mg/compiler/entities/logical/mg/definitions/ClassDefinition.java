package cz.mg.compiler.entities.logical.mg.definitions;

import cz.mg.compiler.utilities.debug.Text;


public class ClassDefinition extends DatatypeDefinition {
    public ClassDefinition(Text name, Inheritance inheritance) {
        super(name, inheritance);
    }
}
