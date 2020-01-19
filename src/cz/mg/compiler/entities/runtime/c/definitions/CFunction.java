package cz.mg.compiler.entities.runtime.c.definitions;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.runtime.c.definitions.command.CCommand;
import cz.mg.collections.text.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class CFunction extends CFunctionSignature {
    @Part
    private final ChainList<CVariable> variables = new ChainList<>();

    @Part
    private final ChainList<CCommand> commands = new ChainList<>();

    private boolean inline = false;

    public CFunction(Trace trace, Text name, ChainList<CVariable> input, CVariable output) {
        super(trace, name, input, output);
    }

    public ChainList<CVariable> getVariables() {
        return variables;
    }

    public ChainList<CCommand> getCommands() {
        return commands;
    }

    public boolean isInline() {
        return inline;
    }

    public void setInline(boolean inline) {
        this.inline = inline;
    }
}
