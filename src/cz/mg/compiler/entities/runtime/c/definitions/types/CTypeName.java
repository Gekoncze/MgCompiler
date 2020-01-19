package cz.mg.compiler.entities.runtime.c.definitions.types;

import cz.mg.collections.text.Text;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.Trace;


public class CTypeName extends CType {
    @Info
    private final Text name;

    public CTypeName(Trace trace, int pointer, Text name) {
        super(trace, pointer);
        this.name = name;
    }

    public Text getName() {
        return name;
    }
}
