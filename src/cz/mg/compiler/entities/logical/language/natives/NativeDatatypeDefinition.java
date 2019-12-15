package cz.mg.compiler.entities.logical.language.natives;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.language.NamedLanguageEntity;
import cz.mg.compiler.entities.logical.language.other.Variable;
import cz.mg.compiler.utilities.debug.Text;


public abstract class NativeDatatypeDefinition extends NamedLanguageEntity {
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
