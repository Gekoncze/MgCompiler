package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.commands.Command;
import cz.mg.compiler.entities.logical.language.other.Variable;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.Rules;


public abstract class BuildCommandTask extends BlockBuildTask {
    protected Command command = null;
    private ChainList<Variable> declaredVariables = new CachedChainList<>();

    public BuildCommandTask(Block block, Context context) {
        super(block, context);
    }

    public Command getCommand() {
        return command;
    }

    public ChainList<Variable> getDeclaredVariables() {
        return declaredVariables;
    }

    @Override
    protected Rules getRules() {
        return null;
    }
}
