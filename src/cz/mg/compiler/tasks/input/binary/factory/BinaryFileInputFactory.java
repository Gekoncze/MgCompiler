package cz.mg.compiler.tasks.input.binary.factory;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.tasks.input.binary.BinaryFileInputTask;
import cz.mg.compiler.tasks.input.binary.BinaryInputTask;


public class BinaryFileInputFactory implements BinaryInputFactory {
    @Info
    private final ReadonlyText workingDirectory;

    public BinaryFileInputFactory(ReadonlyText workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    public BinaryInputTask create(ReadonlyText url) {
        return new BinaryFileInputTask(workingDirectory, url);
    }
}
