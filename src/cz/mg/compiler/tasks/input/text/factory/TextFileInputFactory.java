package cz.mg.compiler.tasks.input.text.factory;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.tasks.input.text.TextFileInputTask;
import cz.mg.compiler.tasks.input.text.TextInputTask;


public class TextFileInputFactory implements TextInputFactory {
    @Info
    private final ReadonlyText workingDirectory;

    public TextFileInputFactory(ReadonlyText workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    public TextInputTask create(ReadonlyText url) {
        return new TextFileInputTask(workingDirectory, url);
    }
}
