package cz.mg.compiler.entities.structured.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.groups.UnitedGroup;


public class OtherGroup extends Part implements UnitedGroup {
    @Child
    private final ChainList<Part> parts;

    public OtherGroup(ChainList<Part> parts) {
        super(merge(parts));
        this.parts = parts;
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
