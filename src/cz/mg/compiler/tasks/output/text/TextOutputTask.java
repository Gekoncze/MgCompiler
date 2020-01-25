package cz.mg.compiler.tasks.output.text;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.tasks.output.OutputTask;


public abstract class TextOutputTask extends OutputTask {
    @Link
    protected final ReadonlyText text;

    protected TextOutputTask(ReadonlyText text) {
        this.text = text;
    }
}
