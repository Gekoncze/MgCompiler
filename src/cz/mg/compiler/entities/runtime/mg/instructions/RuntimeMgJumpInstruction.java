package cz.mg.compiler.entities.runtime.mg.instructions;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.Trace;


public class RuntimeMgJumpInstruction extends RuntimeMgInstruction {
    @Info
    private final int instructionIndex;

    public RuntimeMgJumpInstruction(Trace trace, int instructionIndex) {
        super(trace);
        this.instructionIndex = instructionIndex;
    }

    public int getInstructionIndex() {
        return instructionIndex;
    }
}
