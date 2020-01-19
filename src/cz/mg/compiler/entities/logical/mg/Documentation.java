package cz.mg.compiler.entities.logical.mg;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.logical.LogicalEntity;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class Documentation extends LogicalEntity {
    private final ChainList<Text> lines = new CachedChainList<>();

    public Documentation(Trace trace) {
        super(trace);
    }

    public ChainList<Text> getLines() {
        return lines;
    }
}
