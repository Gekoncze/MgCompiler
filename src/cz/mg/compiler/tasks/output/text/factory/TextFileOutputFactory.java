package cz.mg.compiler.tasks.output.text.factory;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.tasks.output.text.TextFileOutputTask;
import cz.mg.compiler.tasks.output.text.TextOutputTask;
import cz.mg.compiler.utilities.debug.Text;


public class TextFileOutputFactory implements TextOutputFactory {
    @Info
    private final String workingDirectory;

    public TextFileOutputFactory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    public TextOutputTask create(Text url, ReadonlyText text) {
        return new TextFileOutputTask(workingDirectory, url, text);
    }
}
