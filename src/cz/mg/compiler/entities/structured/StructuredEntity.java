package cz.mg.compiler.entities.structured;

import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;
import cz.mg.compiler.entities.Entity;


public abstract class StructuredEntity extends Entity {
    private final Text content;

    public StructuredEntity(Text content) {
        this.content = content;
    }

    public Text getContent() {
        return content;
    }

    @Override
    public Trace getTrace() {
        return content.getTrace();
    }
}
