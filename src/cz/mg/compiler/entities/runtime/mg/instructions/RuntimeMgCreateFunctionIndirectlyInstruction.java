package cz.mg.compiler.entities.runtime.mg.instructions;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.Trace;


public class RuntimeMgCreateFunctionIndirectlyInstruction extends RuntimeMgInstruction {
    @Info
    private final int variable;

    public RuntimeMgCreateFunctionIndirectlyInstruction(Trace trace, int variable) {
        super(trace);
        this.variable = variable;
    }

    public int getVariable() {
        return variable;
    }
}
