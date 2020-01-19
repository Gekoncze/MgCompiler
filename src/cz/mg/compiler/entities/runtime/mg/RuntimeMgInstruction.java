package cz.mg.compiler.entities.runtime.mg;

import cz.mg.compiler.utilities.debug.Trace;


public abstract class RuntimeMgInstruction extends RuntimeMgEntity {
    protected RuntimeMgInstruction(Trace trace) {
        super(trace);
    }
}
