package cz.mg.compiler.entities.logical.mg.definitions;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.NamedLogicalMgEntity;
import cz.mg.compiler.entities.logical.mg.other.Variable;
import cz.mg.compiler.utilities.debug.Text;


public class StampDefinition extends NamedLogicalMgEntity {
    @Part
    ChainList<Variable> values = new CachedChainList<>();

    public StampDefinition(Text name) {
        super(name);
    }

    public ChainList<Variable> getValues() {
        return values;
    }
}
