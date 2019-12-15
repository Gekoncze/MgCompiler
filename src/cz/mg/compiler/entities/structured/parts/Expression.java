package cz.mg.compiler.entities.structured.parts;

import cz.mg.collections.Collection;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.structured.parts.groups.SeparatedGroup;


public class Expression extends cz.mg.compiler.entities.structured.Part implements SeparatedGroup {
    @Part
    private final cz.mg.compiler.entities.structured.Part part;

    @Part
    private final ChainList<cz.mg.compiler.entities.structured.Part> parts;

    public Expression(cz.mg.compiler.entities.structured.Part part, cz.mg.compiler.entities.structured.Part... parts) {
        super(merge(part, merge((Object[]) parts)));
        this.part = part;
        this.parts = new ChainList<>(parts);
    }

    public Expression(cz.mg.compiler.entities.structured.Part part, Collection<cz.mg.compiler.entities.structured.Part> parts) {
        super(merge(part, merge(parts)));
        this.part = part;
        this.parts = new ChainList<>(parts);
    }

    public cz.mg.compiler.entities.structured.Part getPart() {
        return part;
    }

    @Override
    public ChainList<cz.mg.compiler.entities.structured.Part> getParts() {
        return parts;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent() + "]";
    }
}
