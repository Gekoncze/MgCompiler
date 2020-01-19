package cz.mg.compiler.entities.runtime.c.definitions.types;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.runtime.c.definitions.CFunctionSignature;
import cz.mg.compiler.utilities.debug.Trace;


public class CFunctionType extends CType {
    @Part
    private final CFunctionSignature function;

    public CFunctionType(Trace trace, int pointer, CFunctionSignature function) {
        super(trace, pointer);
        this.function = function;
    }

    public CFunctionSignature getFunction() {
        return function;
    }
}
