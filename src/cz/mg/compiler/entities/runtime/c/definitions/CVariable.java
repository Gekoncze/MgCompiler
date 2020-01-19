package cz.mg.compiler.entities.runtime.c.definitions;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.runtime.c.definitions.types.CType;
import cz.mg.collections.text.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class CVariable extends CDefinition {
    @Info
    private final Text name;

    @Part
    private final CType type;

    public CVariable(Trace trace, Text name, CType type) {
        super(trace);
        this.name = name;
        this.type = type;
    }

    public Text getName() {
        return name;
    }

    public CType getType() {
        return type;
    }
}
