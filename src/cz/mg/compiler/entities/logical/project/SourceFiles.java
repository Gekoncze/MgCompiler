package cz.mg.compiler.entities.logical.project;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Child;
import cz.mg.compiler.utilities.debug.Trace;


public class SourceFiles extends ProjectEntity {
    @Child
    private final ChainList<FilePath> files = new CachedChainList<>();

    public SourceFiles(Trace trace) {
        super(trace);
    }

    public ChainList<FilePath> getFiles() {
        return files;
    }
}
