package cz.mg.compiler.entities.runtime.c.definitions.command;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.runtime.c.CEntity;
import cz.mg.compiler.entities.runtime.c.definitions.CVariable;
import cz.mg.compiler.utilities.debug.Trace;


public class CFunctionCallCommand extends CEntity {
    @Part
    private final ChainList<CVariable> input;

    @Part
    private final CVariable output;

    public CFunctionCallCommand(Trace trace, ChainList<CVariable> input, CVariable output) {
        super(trace);
        this.input = input;
        this.output = output;
    }

    public ChainList<CVariable> getInput() {
        return input;
    }

    public CVariable getOutput() {
        return output;
    }
}
