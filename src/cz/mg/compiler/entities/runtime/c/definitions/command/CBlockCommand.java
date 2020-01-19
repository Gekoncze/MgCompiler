package cz.mg.compiler.entities.runtime.c.definitions.command;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.runtime.c.definitions.CVariable;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class CBlockCommand extends CCommand {
    @Part
    private final ChainList<CVariable> variables = new ChainList<>();

    public CBlockCommand(Trace trace) {
        super(trace);
    }

    public ChainList<CVariable> getVariables() {
        return variables;
    }
}
