package cz.mg.compiler.entities.structured.parts.groups;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.structured.Part;


public interface Group {
    public ChainList<Part> getParts();
}
