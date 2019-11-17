package cz.mg.compiler.entities.structured.parts;

import cz.mg.compiler.Child;
import cz.mg.compiler.entities.structured.Part;


public class Declaration extends Part {
    @Child
    private final Type type;

    @Child
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
