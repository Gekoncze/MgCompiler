package cz.mg.compiler.entities.runtime.c.definitions;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Named;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Part;
import cz.mg.collections.text.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class CStructure extends CDefinition implements Named {
    @Info
    private final Text name;

    @Part
    private final ChainList<CVariable> variables = new ChainList<>();

    public CStructure(Trace trace, Text name) {
        super(trace);
        this.name = name;
    }

    @Override
    public Text getName() {
        return name;
    }

    public ChainList<CVariable> getVariables() {
        return variables;
    }
}
