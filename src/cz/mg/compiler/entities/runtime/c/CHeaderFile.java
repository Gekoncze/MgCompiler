package cz.mg.compiler.entities.runtime.c;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.collections.text.Text;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.Trace;


public class CHeaderFile extends CFile {
    @Info
    private final Text guards;

    public CHeaderFile(Trace trace, ReadonlyText name, Text guards) {
        super(trace, name);
        this.guards = guards;
    }

    public Text getGuards() {
        return guards;
    }
}
