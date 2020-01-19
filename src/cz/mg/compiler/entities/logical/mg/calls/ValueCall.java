package cz.mg.compiler.entities.logical.mg.calls;

import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class ValueCall extends Call {
    private final Text value;

    public ValueCall(Trace trace, Text value) {
        super(trace);
        this.value = value;
    }

    public Text getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + value + "]";
    }
}
