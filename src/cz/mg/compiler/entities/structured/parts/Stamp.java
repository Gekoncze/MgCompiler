package cz.mg.compiler.entities.structured.parts;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.structured.parts.chains.List;


public class Stamp extends cz.mg.compiler.entities.structured.Part {
    @Part
    private final Special special;

    @Part
    private final Name name;

    @Part
    private final List arguments;

    public Stamp(Special special, Name name, List arguments) {
        super(merge(special, name, arguments));
        this.special = special;
        this.name = name;
        this.arguments = arguments;
    }

    public Special getSpecial() {
        return special;
    }

    public Name getName() {
        return name;
    }

    public List getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent() + "]";
    }
}
