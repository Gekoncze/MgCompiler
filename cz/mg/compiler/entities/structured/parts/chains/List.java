package cz.mg.compiler.entities.structured.parts.chains;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.groups.SeparatedGroup;


public class List extends Chain implements SeparatedGroup {
    public List(ChainList<Part> parts) {
        super(parts);
    }
}
