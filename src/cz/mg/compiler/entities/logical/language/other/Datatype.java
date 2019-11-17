package cz.mg.compiler.entities.logical.language.other;

import cz.mg.compiler.entities.logical.language.LanguageEntity;
import cz.mg.compiler.entities.logical.language.links.DatatypeDefinitionLink;
import cz.mg.compiler.utilities.debug.Trace;


public class Datatype extends LanguageEntity {
    private final DatatypeDefinitionLink datatypeDefinition;
    private final Storage storage;
    private final int storageLevel;

    public Datatype(Trace trace, DatatypeDefinitionLink datatypeDefinition, Storage storage, int storageLevel) {
        super(trace);
        this.datatypeDefinition = datatypeDefinition;
        this.storage = storage;
        this.storageLevel = storageLevel;
    }

    public DatatypeDefinitionLink getDatatypeDefinition() {
        return datatypeDefinition;
    }

    public Storage getStorage() {
        return storage;
    }

    public int getStorageLevel() {
        return storageLevel;
    }

    public enum Storage {
        ASSOCIATION,
        AGGREGATION,
        COMPOSITION
    }

    @Override
    public String toString() {
        return super.toString() + " [" + datatypeDefinition.getName() + "] [" + storage + "] " + "[" + storageLevel + "]";
    }
}
