package cz.mg.compiler.entities.runtime.mg;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class RuntimeMgFunction extends RuntimeMgEntity {
    @Info
    private final Text name;

    @Part
    private final ChainList<RuntimeMgVariable> input = new ChainList<>();

    @Part
    private final ChainList<RuntimeMgVariable> output = new ChainList<>();

    @Part
    private final ChainList<RuntimeMgVariable> variables = new ChainList<>();

    @Part
    private final ChainList<RuntimeMgCommand> commands = new ChainList<>();

    @Part
    private final ChainList<RuntimeMgInstruction> instructions = new ChainList<>();

    public RuntimeMgFunction(Text name) {
        super(name.getTrace());
        this.name = name;
    }

    public RuntimeMgFunction(Trace trace) {
        super(trace);
        this.name = null;
    }

    public Text getName() {
        return name;
    }

    public ChainList<RuntimeMgVariable> getInput() {
        return input;
    }

    public ChainList<RuntimeMgVariable> getOutput() {
        return output;
    }

    public ChainList<RuntimeMgVariable> getVariables() {
        return variables;
    }

    public ChainList<RuntimeMgCommand> getCommands() {
        return commands;
    }

    public ChainList<RuntimeMgInstruction> getInstructions() {
        return instructions;
    }
}
