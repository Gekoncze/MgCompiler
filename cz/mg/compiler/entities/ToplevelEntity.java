package cz.mg.compiler.entities;

import cz.mg.compiler.utilities.debug.Trace;


public abstract class ToplevelEntity extends Entity {
    @Override
    public Trace getTrace() {
        return new Trace();
    }
}
