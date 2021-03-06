package cz.mg.compiler.entities.structured;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.ToplevelEntity;


public class Structure extends ToplevelEntity {
    @Part
    private final ChainList<Container> pages = new CachedChainList<>();

    public Structure() {
    }

    public ChainList<Container> getPages() {
        return pages;
    }
}
