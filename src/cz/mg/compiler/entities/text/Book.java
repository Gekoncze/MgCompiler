package cz.mg.compiler.entities.text;

import cz.mg.collections.list.List;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.ToplevelEntity;


public class Book extends ToplevelEntity {
    @Child
    private final List<Page> pages = new CachedChainList<>();

    public Book() {
    }

    public List<Page> getPages() {
        return pages;
    }
}
