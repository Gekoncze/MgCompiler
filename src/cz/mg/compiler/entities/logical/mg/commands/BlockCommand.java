package cz.mg.compiler.entities.logical.mg.commands;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.other.Variable;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class BlockCommand extends Command {
    @Part
    private final ChainList<Command> commands = new CachedChainList<>();

    @Part
    private final ChainList<Variable> declaredVariables = new CachedChainList<>();

    public BlockCommand(Trace trace) {
        super(trace);
    }

    public ChainList<Command> getCommands() {
        return commands;
    }

    public ChainList<Variable> getDeclaredVariables() {
        return declaredVariables;
    }
}
