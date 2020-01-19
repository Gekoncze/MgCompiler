package cz.mg.compiler.entities.logical.mg;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.usings.Using;


public class Context extends LogicalMgEntity {
    private final Location location;

    @Part
    private final ChainList<Using> usings = new CachedChainList<>();

    public Context(Location location) {
        super(location.getTrace());
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public ChainList<Using> getUsings() {
        return usings;
    }
}
