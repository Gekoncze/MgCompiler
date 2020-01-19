package cz.mg.compiler.entities.runtime.mg.instructions;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.Trace;


public class RuntimeMgConditionalJumpInstruction extends RuntimeMgInstruction {
    @Info
    private final int instructionIndex;

    @Info
    private final int variableIndex;

    public RuntimeMgConditionalJumpInstruction(Trace trace, int instructionIndex, int variableIndex) {
        super(trace);
        this.instructionIndex = instructionIndex;
        this.variableIndex = variableIndex;
    }

    public int getInstructionIndex() {
        return instructionIndex;
    }

    public int getVariableIndex() {
        return variableIndex;
    }
}
