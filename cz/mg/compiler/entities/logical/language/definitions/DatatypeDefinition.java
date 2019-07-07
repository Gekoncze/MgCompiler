package cz.mg.compiler.entities.logical.language.definitions;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Child;
import cz.mg.compiler.entities.logical.language.NamedLanguageEntity;
import cz.mg.compiler.entities.logical.language.links.DatatypeDefinitionLink;
import cz.mg.compiler.entities.logical.language.other.Property;
import cz.mg.compiler.utilities.debug.Text;


public abstract class DatatypeDefinition extends NamedLanguageEntity {
    private final Inheritance inheritance;

    @Child
    private final ChainList<DatatypeDefinitionLink> bases = new CachedChainList<>();

    @Child
    private final ChainList<Property> properties = new CachedChainList<>();

    @Child
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
