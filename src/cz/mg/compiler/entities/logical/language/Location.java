package cz.mg.compiler.entities.logical.language;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.utilities.debug.Text;


public class Location extends NamedLanguageEntity {
    @Child
    private final ChainList<LanguageEntity> entities = new CachedChainList<>();

    public Location(Text name) {
        super(name);
    }

    public ChainList<LanguageEntity> getEntities() {
        return entities;
    }
}
