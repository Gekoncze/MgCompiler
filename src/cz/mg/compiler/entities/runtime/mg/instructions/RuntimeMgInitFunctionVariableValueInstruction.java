package cz.mg.compiler.entities.runtime.mg.instructions;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.Trace;


public class RuntimeMgInitFunctionVariableValueInstruction extends RuntimeMgInstruction {
    @Info
    private final int sourceVariable;

    @Info
    private final int destinationVariable;

    @Info
    private final int sizeInBytes;

    public RuntimeMgInitFunctionVariableValueInstruction(Trace trace, int sourceVariable, int destinationVariable, int sizeInBytes) {
        super(trace);
        this.sourceVariable = sourceVariable;
        this.destinationVariable = destinationVariable;
        this.sizeInBytes = sizeInBytes;
    }

    public int getSourceVariable() {
        return sourceVariable;
    }

    public int getDestinationVariable() {
        return destinationVariable;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }
}
