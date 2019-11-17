package cz.mg.compiler.entities.structured.parts;

import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.structured.Part;


public class Value extends Part {
    public Value(Text content) {
        super(content);
    }

    public Text getValue(){
        return getContent().slice(1, getContent().count() - 1);
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent() + "]";
    }
}
