package cz.mg.compiler.entities.runtime.c.definitions.command;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.runtime.c.definitions.CVariable;
import cz.mg.compiler.utilities.debug.Trace;


public class CIfCommand extends CBlockCommand {
    @Link
    private final CVariable variable;

    public CIfCommand(Trace trace, CVariable variable) {
        super(trace);
        this.variable = variable;
    }

    public CVariable getVariable() {
        return variable;
    }
}
