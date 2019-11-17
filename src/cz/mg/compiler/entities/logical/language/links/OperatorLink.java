package cz.mg.compiler.entities.logical.language.links;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.definitions.FunctionDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class OperatorLink extends Link<FunctionDefinition> {
    private final Text operator;

    public OperatorLink(Context context, Text operator) {
        super(operator.getTrace(), context);
        this.operator = operator;
    }

    public Text getOperator() {
        return operator;
    }
}
