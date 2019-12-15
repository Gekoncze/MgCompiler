package cz.mg.compiler.entities.structured.parts;

import cz.mg.compiler.annotations.Part;


public class Declaration extends cz.mg.compiler.entities.structured.Part {
    @Part
    private final Type type;

    @Part
    private final Name name;

    public Declaration(Type type, Name name) {
        super(merge(type, name));
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public Name getName() {
        return name;
    }
}
