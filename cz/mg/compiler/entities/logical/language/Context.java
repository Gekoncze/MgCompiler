package cz.mg.compiler.entities.logical.language;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Child;
import cz.mg.compiler.entities.logical.language.usings.Using;


public class Context extends LanguageEntity {
    private final Location location;

    @Child
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
