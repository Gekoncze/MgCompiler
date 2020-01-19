package cz.mg.compiler.entities.runtime.mg.commands;

import cz.mg.compiler.entities.runtime.mg.RuntimeMgEntity;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class RuntimeMgCommand extends RuntimeMgEntity {
    public RuntimeMgCommand(Trace trace) {
        super(trace);
    }
}
