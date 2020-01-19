package cz.mg.compiler.entities.logical.mg.calls;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.links.NamedLink;
import cz.mg.compiler.utilities.debug.Trace;


public class NameCall extends Call {
    @Part
    private final NamedLink target;

    public NameCall(Trace trace, NamedLink target) {
        super(trace);
        this.target = target;
    }

    public NamedLink getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + target.getName() + "]";
    }
}
