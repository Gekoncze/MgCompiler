package cz.mg.compiler.entities.runtime;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.ToplevelEntity;
import cz.mg.compiler.entities.runtime.mg.RuntimeMg;


public class Runtime extends ToplevelEntity {
    @Part
    private RuntimeMg runtimeMg;

    public Runtime() {
    }

    public RuntimeMg getRuntimeMg() {
        return runtimeMg;
    }
}
