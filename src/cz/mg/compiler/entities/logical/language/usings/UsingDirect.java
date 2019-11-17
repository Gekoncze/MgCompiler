package cz.mg.compiler.entities.logical.language.usings;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Child;
import cz.mg.compiler.entities.logical.language.links.NamedLink;
import cz.mg.compiler.utilities.debug.Trace;


public class UsingDirect extends Using {
    @Child
    private final ChainList<NamedLink> path;

    public UsingDirect(Trace trace, ChainList<NamedLink> path) {
        super(trace);
        this.path = path;
    }

    public ChainList<NamedLink> getPath() {
        return path;
    }
}
