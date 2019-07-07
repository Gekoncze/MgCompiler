package cz.mg.compiler.tasks.resolver;

import cz.mg.compiler.tasks.Task;


public abstract class ResolverTask extends Task {
    public ResolverTask(Task parentTask) {
        super(parentTask);
    }
}
