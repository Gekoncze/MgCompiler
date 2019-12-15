package cz.mg.compiler.entities.structured.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.structured.parts.groups.UnitedGroup;


public class OtherGroup extends cz.mg.compiler.entities.structured.Part implements UnitedGroup {
    @Part
    private final ChainList<cz.mg.compiler.entities.structured.Part> parts;

    public OtherGroup(ChainList<cz.mg.compiler.entities.structured.Part> parts) {
        super(merge(parts));
        this.parts = parts;
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
