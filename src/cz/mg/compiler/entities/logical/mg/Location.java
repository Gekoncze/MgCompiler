package cz.mg.compiler.entities.logical.mg;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.utilities.debug.Text;


public class Location extends NamedLogicalMgEntity {
    @Part
    private final ChainList<LogicalMgEntity> entities = new CachedChainList<>();

    public Location(Text name) {
        super(name);
    }

    public ChainList<LogicalMgEntity> getEntities() {
        return entities;
    }
}
