package cz.mg.compiler.entities.text;

import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;
import cz.mg.compiler.entities.Entity;


public abstract class TextEntity extends Entity {
    private final Text content;

    public TextEntity(Text content) {
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
