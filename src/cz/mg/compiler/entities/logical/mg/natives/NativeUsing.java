package cz.mg.compiler.entities.logical.mg.natives;

import cz.mg.compiler.entities.logical.mg.usings.Using;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class NativeUsing extends Using {
    private final Text path;

    public NativeUsing(Trace trace, Text path) {
        super(trace);
        this.path = path;
    }

    public Text getPath() {
        return path;
    }
}
