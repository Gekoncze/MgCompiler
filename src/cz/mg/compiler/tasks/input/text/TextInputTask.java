package cz.mg.compiler.tasks.input.text;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.tasks.input.InputTask;
import cz.mg.compiler.utilities.debug.Text;


public abstract class TextInputTask extends InputTask {
    @Link
    protected Text text = null;

    public TextInputTask() {
    }

    public Text getText() {
        return text;
    }
}
