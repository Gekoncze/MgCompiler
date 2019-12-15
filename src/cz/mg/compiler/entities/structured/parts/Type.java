package cz.mg.compiler.entities.structured.parts;

import cz.mg.compiler.annotations.Part;


public class Type extends cz.mg.compiler.entities.structured.Part {
    @Part
    private final Name name;

    @Part
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
