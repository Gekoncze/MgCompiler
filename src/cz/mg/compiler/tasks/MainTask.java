package cz.mg.compiler.tasks;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.Entities;


public abstract class MainTask extends Task {
    @Link
    private Entities entities = null;

    public MainTask() {
        super(null);
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }
}
