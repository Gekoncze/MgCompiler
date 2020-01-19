package cz.mg.compiler.entities.logical.mg.natives;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.NamedLogicalMgEntity;
import cz.mg.compiler.entities.logical.mg.other.Variable;
import cz.mg.compiler.utilities.debug.Text;


public abstract class NativeDatatypeDefinition extends NamedLogicalMgEntity {
    @Part
    private final ChainList<Variable> properties = new CachedChainList<>();

    @Part
    private final ChainList<NativeFunctionDefinition> functions = new CachedChainList<>();

    public NativeDatatypeDefinition(Text name) {
        super(name);
    }

    public ChainList<Variable> getProperties() {
        return properties;
    }

    public ChainList<NativeFunctionDefinition> getFunctions() {
        return functions;
    }
}
