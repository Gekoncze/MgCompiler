package cz.mg.compiler.entities.logical.language.calls;

import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.logical.language.links.OtherLink;
import cz.mg.compiler.utilities.debug.Trace;


public class OtherCall extends Call {
    @Child
    private final OtherLink target;

    public OtherCall(Trace trace, OtherLink target) {
        super(trace);
        this.target = target;
    }

    public OtherLink getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + target.getName() + "]";
    }
}
