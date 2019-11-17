package cz.mg.compiler.entities.structured.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Child;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.groups.UnitedGroup;


public class Colon extends Part implements UnitedGroup {
    @Child
    private final Special colon;

    @Child
    private final ChainList<Part> parts;

    public Colon(ChainList<Part> parts, Special colon) {
        super(merge(colon, merge(parts)));
        this.colon = colon;
        this.parts = parts;
    }

    public Special getColon() {
        return colon;
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
