package cz.mg.compiler.entities.structured.parts;

import cz.mg.compiler.Child;
import cz.mg.compiler.entities.structured.Part;


public class Type extends Part {
    @Child
    private final Name name;

    @Child
    private final Special special;

    public Type(Name name, Special special) {
        super(merge(name, special));
        this.name = name;
        this.special = special;
    }

    public Name getName() {
        return name;
    }

    public Special getSpecial() {
        return special;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent() + "]";
    }
}
