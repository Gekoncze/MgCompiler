package cz.mg.compiler.entities.logical.mg.links;

import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.definitions.FunctionDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class FunctionDefinitionLink extends Link<FunctionDefinition> {
    private final Text name;

    public FunctionDefinitionLink(Context context, Text name) {
        super(name.getTrace(), context);
        this.name = name;
    }

    public Text getName() {
        return name;
    }
}
