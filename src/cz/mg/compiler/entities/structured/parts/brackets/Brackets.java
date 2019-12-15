package cz.mg.compiler.entities.structured.parts.brackets;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.structured.parts.Special;
import cz.mg.compiler.entities.structured.parts.groups.UnitedGroup;


public abstract class Brackets extends cz.mg.compiler.entities.structured.Part implements UnitedGroup {
    @Part
    private final Special left;

    @Part
    private final ChainList<cz.mg.compiler.entities.structured.Part> parts;

    @Part
    private final Special right;

    public Brackets(ChainList<cz.mg.compiler.entities.structured.Part> parts, Special left, Special right) {
        super(merge(left, (merge(parts)), right));
        this.left = left;
        this.parts = parts;
        this.right = right;
    }

    public Special getLeft() {
        return left;
    }

    @Override
    public ChainList<cz.mg.compiler.entities.structured.Part> getParts() {
        return parts;
    }

    public Special getRight() {
        return right;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent() + "]";
    }
}
