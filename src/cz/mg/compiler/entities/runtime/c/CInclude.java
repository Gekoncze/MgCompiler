package cz.mg.compiler.entities.runtime.c;

import cz.mg.collections.text.Text;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.Trace;


public class CInclude extends CEntity {
    @Info
    private final boolean local;

    @Info
    private final Text path;

    public CInclude(Trace trace, boolean local, Text path) {
        super(trace);
        this.local = local;
        this.path = path;
    }

    public boolean isLocal() {
        return local;
    }

    public Text getPath() {
        return path;
    }
}
