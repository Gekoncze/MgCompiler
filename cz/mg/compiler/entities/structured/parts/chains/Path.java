package cz.mg.compiler.entities.structured.parts.chains;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.groups.SeparatedGroup;


public class Path extends Chain implements SeparatedGroup {
    public Path(ChainList<Part> parts) {
        super(parts);
    }
}
