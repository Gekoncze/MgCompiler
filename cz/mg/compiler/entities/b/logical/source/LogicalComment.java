package cz.mg.compiler.entities.b.logical.source;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalComment extends Logical {
    private final String text;

    public LogicalComment(Logical parent, Location location, String text) {
        super(parent, location);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
