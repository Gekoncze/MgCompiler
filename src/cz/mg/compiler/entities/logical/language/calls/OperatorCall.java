package cz.mg.compiler.entities.logical.language.calls;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.language.links.OperatorLink;
import cz.mg.compiler.utilities.debug.Trace;


public class OperatorCall extends Call {
    @Part
    private final OperatorLink target;

    public OperatorCall(Trace trace, OperatorLink target) {
        super(trace);
        this.target = target;
    }

    public OperatorLink getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + target.getOperator() + "]";
    }
}
