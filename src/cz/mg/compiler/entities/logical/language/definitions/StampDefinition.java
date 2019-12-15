package cz.mg.compiler.entities.logical.language.definitions;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.language.NamedLanguageEntity;
import cz.mg.compiler.entities.logical.language.other.Variable;
import cz.mg.compiler.utilities.debug.Text;


public class StampDefinition extends NamedLanguageEntity {
    @Part
    ChainList<Variable> values = new CachedChainList<>();

    public StampDefinition(Text name) {
        super(name);
    }

    public ChainList<Variable> getValues() {
        return values;
    }
}
