package cz.mg.compiler.entities.runtime.c.definitions;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Named;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Part;
import cz.mg.collections.text.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class CFunctionSignature extends CDefinition implements Named {
    @Info
    private final Text name;

    @Part
    private final ChainList<CVariable> input;

    @Part
    private final CVariable output;

    public CFunctionSignature(Trace trace, Text name, ChainList<CVariable> input, CVariable output) {
        super(trace);
        this.name = name;
        this.input = input;
        this.output = output;
    }

    @Override
    public Text getName() {
        return name;
    }

    public ChainList<CVariable> getInput() {
        return input;
    }

    public CVariable getOutput() {
        return output;
    }
}
