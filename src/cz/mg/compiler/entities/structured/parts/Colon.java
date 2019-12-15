package cz.mg.compiler.entities.structured.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.structured.parts.groups.UnitedGroup;


public class Colon extends cz.mg.compiler.entities.structured.Part implements UnitedGroup {
    @Part
    private final Special colon;

    @Part
    private final ChainList<cz.mg.compiler.entities.structured.Part> parts;

    public Colon(ChainList<cz.mg.compiler.entities.structured.Part> parts, Special colon) {
        super(merge(colon, merge(parts)));
        this.colon = colon;
        this.parts = parts;
    }

    public Special getColon() {
        return colon;
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
