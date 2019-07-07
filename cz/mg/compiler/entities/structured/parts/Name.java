package cz.mg.compiler.entities.structured.parts;

import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.structured.Part;


public class Name extends Part {
    public Name(Text content) {
        super(content);
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent() + "]";
    }
}
