package cz.mg.compiler.entities.structured.parts.chains;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Child;
import cz.mg.compiler.entities.structured.Part;


public abstract class Chain extends Part {
    @Child
    private final ChainList<Part> parts;

    public Chain(ChainList<Part> parts) {
        super(merge(parts));
        this.parts = parts;
    }

    public ChainList<Part> getParts() {
        return parts;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent() + "]";
    }
}
