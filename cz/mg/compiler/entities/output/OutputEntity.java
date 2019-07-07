package cz.mg.compiler.entities.output;

import cz.mg.compiler.entities.Entity;


public abstract class OutputEntity extends Entity {
    public abstract void save(String code);
}
