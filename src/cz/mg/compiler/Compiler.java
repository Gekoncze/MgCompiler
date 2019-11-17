package cz.mg.compiler;

import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.Entities;
import cz.mg.compiler.tasks.MainTask;


public class Compiler extends Element {
    @Child
    private final Entities entities = new Entities();

    @Child
    private final MainTask mainTask;

    public Compiler(MainTask mainTask) {
        this.mainTask = mainTask;
        mainTask.setEntities(entities);
    }

    public Entities getEntities() {
        return entities;
    }

    public MainTask getMainTask() {
        return mainTask;
    }

    public void run(){
        mainTask.tryToRun();
    }
}
