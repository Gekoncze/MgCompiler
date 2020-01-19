package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.commands.Command;
import cz.mg.compiler.entities.logical.mg.other.Variable;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.Rules;


public abstract class BuildCommandTask extends BlockBuildTask {
    @Link
    protected Command command = null;

    @Link
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
