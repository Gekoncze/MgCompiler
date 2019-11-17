package cz.mg.compiler.entities.text;

import cz.mg.compiler.utilities.debug.Text;


public abstract class Token extends TextEntity {
    public Token(Text content) {
        super(content);
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent().toString() + "]";
    }
}
