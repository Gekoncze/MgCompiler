package cz.mg.compiler.entities.structured.parts;

import cz.mg.collections.Collection;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.groups.SeparatedGroup;


public class Expression extends Part implements SeparatedGroup {
    @Child
    private final Part part;

    @Child
    private final ChainList<Part> parts;

    public Expression(Part part, Part... parts) {
        super(merge(part, merge((Object[]) parts)));
        this.part = part;
        this.parts = new ChainList<>(parts);
    }

    public Expression(Part part, Collection<Part> parts) {
        super(merge(part, merge(parts)));
        this.part = part;
        this.parts = new ChainList<>(parts);
    }

    public Part getPart() {
        return part;
    }

    @Override
    public ChainList<Part> getParts() {
        return parts;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent() + "]";
    }
}
