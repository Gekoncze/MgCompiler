package cz.mg.compiler.entities.structured;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.ToplevelEntity;


public class StructuredBook extends ToplevelEntity {
    @Child
    private final ChainList<StructuredPage> pages = new CachedChainList<>();

    public StructuredBook() {
    }

    public ChainList<StructuredPage> getPages() {
        return pages;
    }
}
