package cz.mg.compiler.entities.logical.mg.natives;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.NamedLogicalMgEntity;
import cz.mg.compiler.entities.logical.mg.other.Variable;
import cz.mg.compiler.utilities.debug.Text;


public class NativeFunctionDefinition extends NamedLogicalMgEntity {
    @Part
    private final ChainList<Variable> input;

    @Part
    private final Variable output;

    @Part
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
