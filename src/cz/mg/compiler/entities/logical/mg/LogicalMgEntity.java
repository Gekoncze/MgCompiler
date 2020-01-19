package cz.mg.compiler.entities.logical.mg;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.LogicalEntity;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class LogicalMgEntity extends LogicalEntity {
    @Part
    private Stamps stamps = null;

    @Part
    private Documentation documentation = null;

    public LogicalMgEntity(Trace trace) {
        super(trace);
    }

    public Stamps getStamps() {
        return stamps;
    }

    public void setStamps(Stamps stamps) {
        this.stamps = stamps;
    }

    public Documentation getDocumentation() {
        return documentation;
    }

    public void setDocumentation(Documentation documentation) {
        this.documentation = documentation;
    }
}
