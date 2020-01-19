package cz.mg.compiler.entities.runtime.c.definitions.command;

import cz.mg.compiler.entities.runtime.c.CEntity;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class CCommand extends CEntity {
    public CCommand(Trace trace) {
        super(trace);
    }
}
