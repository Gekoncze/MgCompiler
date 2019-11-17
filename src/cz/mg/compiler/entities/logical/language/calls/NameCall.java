package cz.mg.compiler.entities.logical.language.calls;

import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.logical.language.links.NamedLink;
import cz.mg.compiler.utilities.debug.Trace;


public class NameCall extends Call {
    @Child
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
