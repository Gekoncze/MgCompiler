package cz.mg.compiler.entities.runtime.c;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.ToplevelEntity;


public class CLanguage extends ToplevelEntity {
    @Part
    private final ChainList<CFile> files = new ChainList<>();

    public CLanguage() {
    }

    public ChainList<CFile> getFiles() {
        return files;
    }
}
