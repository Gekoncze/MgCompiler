package cz.mg.compiler.entities.output;

import cz.mg.collections.list.List;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.ToplevelEntity;


public class Output extends ToplevelEntity {
    @Child
    private final List<OutputEntity> outputs = new CachedChainList<>();

    public Output() {
    }

    public List<OutputEntity> getOutputs() {
        return outputs;
    }
}
