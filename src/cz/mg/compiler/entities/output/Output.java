package cz.mg.compiler.entities.output;

import cz.mg.collections.list.List;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.ToplevelEntity;


public class Output extends ToplevelEntity {
    @Part
    private final List<OutputEntity> outputs = new CachedChainList<>();

    public Output() {
    }

    public List<OutputEntity> getOutputs() {
        return outputs;
    }
}
