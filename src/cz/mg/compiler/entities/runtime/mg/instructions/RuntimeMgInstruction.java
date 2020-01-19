package cz.mg.compiler.entities.runtime.mg.instructions;

import cz.mg.compiler.entities.runtime.mg.RuntimeMgEntity;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class RuntimeMgInstruction extends RuntimeMgEntity {
    public RuntimeMgInstruction(Trace trace) {
        super(trace);
    }
}
