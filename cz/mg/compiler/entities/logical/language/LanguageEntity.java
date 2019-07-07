package cz.mg.compiler.entities.logical.language;

import cz.mg.compiler.entities.logical.LogicalEntity;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class LanguageEntity extends LogicalEntity {
    private Stamps stamps = null;
    private Documentation documentation = null;

    public LanguageEntity(Trace trace) {
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
