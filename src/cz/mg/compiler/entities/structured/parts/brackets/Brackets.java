package cz.mg.compiler.entities.structured.parts.brackets;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.structured.parts.Special;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.groups.UnitedGroup;


public abstract class Brackets extends Part implements UnitedGroup {
    @Child
    private final Special left;

    @Child
    private final ChainList<Part> parts;

    @Child
    private final Special right;

    public Brackets(ChainList<Part> parts, Special left, Special right) {
        super(merge(left, (merge(parts)), right));
        this.left = left;
        this.parts = parts;
        this.right = right;
    }

    public Special getLeft() {
        return left;
    }

    @Override
    public ChainList<Part> getParts() {
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
