package cz.mg.compiler.entities.runtime.c.definitions.types;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.runtime.c.definitions.CStructure;
import cz.mg.compiler.utilities.debug.Trace;


public class CStructureType extends CType {
    @Part
    private final CStructure structure;

    public CStructureType(Trace trace, int pointer, CStructure structure) {
        super(trace, pointer);
        this.structure = structure;
    }

    public CStructure getStructure() {
        return structure;
    }
}
