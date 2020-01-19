package cz.mg.compiler.entities.runtime.c.definitions.types;

import cz.mg.collections.array.Array;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.entities.runtime.c.CEntity;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class CType extends CEntity {
    @Info
    private final int pointer;

    @Info
    private final Array<Boolean> optimized;

    public CType(Trace trace, int pointer) {
        super(trace);
        if(pointer < 0) throw new IllegalArgumentException("CType expected pointer >= 0, but got " + pointer);
        this.pointer = pointer;
        this.optimized = new Array<>(pointer);
    }

    public int getPointer() {
        return pointer;
    }

    public Array<Boolean> getOptimized() {
        return optimized;
    }
}
