package cz.mg.compiler.entities.logical.language.definitions;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.logical.language.commands.Command;
import cz.mg.compiler.entities.logical.language.NamedLanguageEntity;
import cz.mg.compiler.entities.logical.language.other.Variable;
import cz.mg.compiler.utilities.debug.Text;


public class FunctionDefinition extends NamedLanguageEntity {
    @Child
    private final ChainList<Variable> input;

    @Child
    private final ChainList<Variable> output;

    @Child
    private final Text operator;

    @Child
    private final ChainList<Command> commands = new CachedChainList<>();

    @Child
    private final ChainList<Variable> declaredVariables = new CachedChainList<>();

    public FunctionDefinition(Text name, ChainList<Variable> input, ChainList<Variable> output, Text operator) {
        super(name);
        this.input = input;
        this.output = output;
        this.operator = operator;
    }

    public ChainList<Variable> getInput() {
        return input;
    }

    public ChainList<Variable> getOutput() {
        return output;
    }

    public Text getOperator() {
        return operator;
    }

    public ChainList<Command> getCommands() {
        return commands;
    }

    public ChainList<Variable> getDeclaredVariables() {
        return declaredVariables;
    }
}
