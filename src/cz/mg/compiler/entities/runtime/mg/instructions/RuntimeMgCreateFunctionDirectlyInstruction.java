package cz.mg.compiler.entities.runtime.mg.instructions;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.Trace;


public class RuntimeMgCreateFunctionDirectlyInstruction extends RuntimeMgInstruction {
    @Info
    private final long function;

    public RuntimeMgCreateFunctionDirectlyInstruction(Trace trace, long function) {
        super(trace);
        this.function = function;
    }

    public long getFunction() {
        return function;
    }
}
