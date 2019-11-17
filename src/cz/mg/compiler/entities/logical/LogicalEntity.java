package cz.mg.compiler.entities.logical;

import cz.mg.compiler.utilities.debug.Trace;
import cz.mg.compiler.entities.Entity;


public abstract class LogicalEntity extends Entity {
    private final Trace trace;

    protected LogicalEntity(Trace trace) {
        this.trace = trace;
    }

    @Override
    public Trace getTrace() {
        return trace;
    }
}
