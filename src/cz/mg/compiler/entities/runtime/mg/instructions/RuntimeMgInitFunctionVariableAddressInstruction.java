package cz.mg.compiler.entities.runtime.mg.instructions;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.Trace;


public class RuntimeMgInitFunctionVariableAddressInstruction extends RuntimeMgInstruction {
    @Info
    private final int sourceVariable;

    @Info
    private final int destinationVariable;

    public RuntimeMgInitFunctionVariableAddressInstruction(Trace trace, int sourceVariable, int destinationVariable) {
        super(trace);
        this.sourceVariable = sourceVariable;
        this.destinationVariable = destinationVariable;
    }

    public int getSourceVariable() {
        return sourceVariable;
    }

    public int getDestinationVariable() {
        return destinationVariable;
    }
}
