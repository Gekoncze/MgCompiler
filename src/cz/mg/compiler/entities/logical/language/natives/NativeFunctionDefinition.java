package cz.mg.compiler.entities.logical.language.natives;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.logical.language.NamedLanguageEntity;
import cz.mg.compiler.entities.logical.language.other.Variable;
import cz.mg.compiler.utilities.debug.Text;


public class NativeFunctionDefinition extends NamedLanguageEntity {
    @Child
    private final ChainList<Variable> input;

    @Child
    private final Variable output;

    @Child
    private final Text operator;

    public NativeFunctionDefinition(Text name, ChainList<Variable> input, Variable output, Text operator) {
        super(name);
        this.input = input;
        this.output = output;
        this.operator = operator;
    }

    public ChainList<Variable> getInput() {
        return input;
    }

    public Variable getOutput() {
        return output;
    }

    public Text getOperator() {
        return operator;
    }
}
