package cz.mg.compiler.tasks.compiler;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.input.InputEntity;


public class LoadSourceTask extends CompilerTask {
    @Link
    private final InputEntity source;

    public LoadSourceTask(InputEntity source) {
        this.source = source;
    }

    @Override
    protected void onRun() {
        source.load();
    }
}
