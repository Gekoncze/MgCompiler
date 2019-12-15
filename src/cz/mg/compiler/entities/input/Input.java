package cz.mg.compiler.entities.input;

import cz.mg.collections.list.List;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.ToplevelEntity;


public class Input extends ToplevelEntity {
    @Part
    private final List<InputEntity> inputs = new CachedChainList<>();

    public Input() {
    }

    public List<InputEntity> getInputs() {
        return inputs;
    }
}
