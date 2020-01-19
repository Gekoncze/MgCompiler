package cz.mg.compiler.entities.logical.mg.definitions;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.NamedLogicalMgEntity;
import cz.mg.compiler.entities.logical.mg.links.DatatypeDefinitionLink;
import cz.mg.compiler.entities.logical.mg.other.Property;
import cz.mg.compiler.utilities.debug.Text;


public abstract class DatatypeDefinition extends NamedLogicalMgEntity {
    private final Inheritance inheritance;

    @Part
    private final ChainList<DatatypeDefinitionLink> bases = new CachedChainList<>();

    @Part
    private final ChainList<Property> properties = new CachedChainList<>();

    @Part
    private final ChainList<FunctionDefinition> functions = new CachedChainList<>();

    public DatatypeDefinition(Text name, Inheritance inheritance) {
        super(name);
        this.inheritance = inheritance;
    }

    public Inheritance getInheritance() {
        return inheritance;
    }

    public ChainList<DatatypeDefinitionLink> getBases() {
        return bases;
    }

    public ChainList<Property> getProperties() {
        return properties;
    }

    public ChainList<FunctionDefinition> getFunctions() {
        return functions;
    }

    public enum Inheritance {
        IS,
        VIEW,
        ALIAS,
        LIKE
    }
}
