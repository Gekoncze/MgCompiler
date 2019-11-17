package cz.mg.compiler.entities.logical.language.usings;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Child;
import cz.mg.compiler.entities.logical.language.links.NamedLink;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class UsingAlias extends Using {
    @Child
    private final ChainList<NamedLink> path;
    private final Text alias;

    public UsingAlias(Trace trace, ChainList<NamedLink> path, Text alias) {
        super(trace);
        this.path = path;
        this.alias = alias;
    }

    public ChainList<NamedLink> getPath() {
        return path;
    }

    public Text getAlias() {
        return alias;
    }
}
