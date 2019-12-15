package cz.mg.compiler.entities.text;

import cz.mg.collections.list.List;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.ToplevelEntity;


public class Book extends ToplevelEntity {
    @Part
    private final List<Page> pages = new CachedChainList<>();

    public Book() {
    }

    public List<Page> getPages() {
        return pages;
    }
}
