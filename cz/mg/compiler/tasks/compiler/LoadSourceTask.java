package cz.mg.compiler.tasks.compiler;

import cz.mg.compiler.entities.input.InputEntity;
import cz.mg.compiler.tasks.Task;


public class LoadSourceTask extends CompilerTask {
    private final InputEntity source;

    public LoadSourceTask(Task parentTask, InputEntity source) {
        super(parentTask);
        this.source = source;
    }

    @Override
    protected void onRun() {
        source.load();
    }
}
