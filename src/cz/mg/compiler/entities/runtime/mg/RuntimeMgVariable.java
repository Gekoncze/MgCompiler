package cz.mg.compiler.entities.runtime.mg;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class RuntimeMgVariable extends RuntimeMgEntity {
    @Info
    private final Text name;

    public RuntimeMgVariable(Text name) {
        super(name.getTrace());
        this.name = name;
    }

    public RuntimeMgVariable(Trace trace) {
        super(trace);
        this.name = null;
    }

    public Text getName() {
        return name;
    }
}
