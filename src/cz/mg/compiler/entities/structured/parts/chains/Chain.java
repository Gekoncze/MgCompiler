package cz.mg.compiler.entities.structured.parts.chains;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;


public abstract class Chain extends cz.mg.compiler.entities.structured.Part {
    @Part
    private final ChainList<cz.mg.compiler.entities.structured.Part> parts;

    public Chain(ChainList<cz.mg.compiler.entities.structured.Part> parts) {
        super(merge(parts));
        this.parts = parts;
    }

    public ChainList<cz.mg.compiler.entities.structured.Part> getParts() {
        return parts;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent() + "]";
    }
}
