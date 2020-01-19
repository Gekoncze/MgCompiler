package cz.mg.compiler.entities.runtime;

import cz.mg.compiler.entities.Entity;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class RuntimeEntity extends Entity {
    private final Trace trace;

    public RuntimeEntity(Trace trace) {
        this.trace = trace;
    }

    @Override
    public Trace getTrace() {
        return trace;
    }
}
