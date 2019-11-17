package cz.mg.compiler.entities.logical.language.commands;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Child;
import cz.mg.compiler.entities.logical.language.other.Variable;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class BlockCommand extends Command {
    @Child
    private final ChainList<Command> commands = new CachedChainList<>();

    @Child
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
