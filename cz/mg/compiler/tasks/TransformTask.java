package cz.mg.compiler.tasks;

import cz.mg.compiler.entities.Entity;


public interface TransformTask<A extends Task, B extends Task, C extends Object, D extends Entity> extends Task<A, B> {
    public C getInput();
    public D getOutput();
}
