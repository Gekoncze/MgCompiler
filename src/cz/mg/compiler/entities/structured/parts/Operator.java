package cz.mg.compiler.entities.structured.parts;

import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.utilities.debug.Text;


public class Operator extends Part {
    public Operator(Text content) {
        super(content);
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent() + "]";
    }
}
