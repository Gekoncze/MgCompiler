package cz.mg.compiler.entities.logical.language;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.logical.LogicalEntity;
import cz.mg.compiler.entities.logical.language.links.StampLink;
import cz.mg.compiler.utilities.debug.Trace;


public class Stamps extends LogicalEntity {
    @Child
    private final ChainList<StampLink> stamps = new CachedChainList<>();

    public Stamps(Trace trace) {
        super(trace);
    }

    public ChainList<StampLink> getStamps() {
        return stamps;
    }
}
