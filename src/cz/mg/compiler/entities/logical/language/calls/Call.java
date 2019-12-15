package cz.mg.compiler.entities.logical.language.calls;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.language.LanguageEntity;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class Call extends LanguageEntity {
    @Part
    private final ChainList<Call> arguments = new CachedChainList<>();

    public Call(Trace trace) {
        super(trace);
    }

    public ChainList<Call> getArguments() {
        return arguments;
    }
}
