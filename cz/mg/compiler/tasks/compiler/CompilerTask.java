package cz.mg.compiler.tasks.compiler;

import cz.mg.compiler.tasks.Task;


public abstract class CompilerTask extends Task {
    public CompilerTask(Task parentTask) {
        super(parentTask);
    }
}
