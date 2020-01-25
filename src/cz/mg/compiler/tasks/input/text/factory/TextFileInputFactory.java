package cz.mg.compiler.tasks.input.text.factory;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.tasks.input.text.TextFileInputTask;
import cz.mg.compiler.tasks.input.text.TextInputTask;
import cz.mg.compiler.utilities.debug.Text;


public class TextFileInputFactory implements TextInputFactory {
    @Info
    private final String workingDirectory;

    public TextFileInputFactory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    public TextInputTask create(Text url) {
        return new TextFileInputTask(workingDirectory, url);
    }
}
