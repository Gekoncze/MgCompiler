package cz.mg.compiler.tasks.builder;

import cz.mg.compiler.tasks.Task;


public abstract class BuildTask extends Task {
    public BuildTask(Task parentTask) {
        super(parentTask);
    }
}
