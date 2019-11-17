package cz.mg.compiler.tasks.composer;

import cz.mg.compiler.tasks.Task;


public abstract class ComposeTask extends Task {
    public ComposeTask(Task parentTask) {
        super(parentTask);
    }
}
