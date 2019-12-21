package cz.mg.compiler;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.Entities;
import cz.mg.compiler.tasks.Tasks;


public class Compiler extends Element implements Runnable {
    @Part
    private final Entities entities = new Entities();

    @Part
    private final Tasks tasks = new Tasks();

    public Compiler() {
    }

    public Entities getEntities() {
        return entities;
    }

    public Tasks getTasks() {
        return tasks;
    }

    @Override
    public void run(){
        tasks.tryToRun();
    }
}
