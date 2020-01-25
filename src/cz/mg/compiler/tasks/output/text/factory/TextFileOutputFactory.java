package cz.mg.compiler.tasks.output.text.factory;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.tasks.output.text.TextFileOutputTask;
import cz.mg.compiler.tasks.output.text.TextOutputTask;


public class TextFileOutputFactory implements TextOutputFactory {
    @Info
    private final ReadonlyText workingDirectory;

    public TextFileOutputFactory(ReadonlyText workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    public TextOutputTask create(ReadonlyText url, ReadonlyText text) {
        return new TextFileOutputTask(workingDirectory, url, text);
    }
}
