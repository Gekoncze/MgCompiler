package cz.mg.compiler.entities.runtime.mg.instructions;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.Trace;


public class RuntimeMgCopyInstruction extends RuntimeMgInstruction {
    @Info
    private final int sourceVariableIndex;

    @Info
    private final int destinationVariableIndex;

    @Info
    private final int sizeInBytes;

    public RuntimeMgCopyInstruction(Trace trace, int sourceVariableIndex, int destinationVariableIndex, int sizeInBytes) {
        super(trace);
        this.sourceVariableIndex = sourceVariableIndex;
        this.destinationVariableIndex = destinationVariableIndex;
        this.sizeInBytes = sizeInBytes;
    }

    public int getSourceVariableIndex() {
        return sourceVariableIndex;
    }

    public int getDestinationVariableIndex() {
        return destinationVariableIndex;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }
}
