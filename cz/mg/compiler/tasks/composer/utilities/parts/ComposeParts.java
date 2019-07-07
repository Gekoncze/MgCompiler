package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.structured.Part;


public interface ComposeParts {
    public void compose(ChainList<Part> parts);
}
