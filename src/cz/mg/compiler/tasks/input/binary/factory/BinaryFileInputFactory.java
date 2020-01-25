package cz.mg.compiler.tasks.input.binary.factory;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.tasks.input.binary.BinaryFileInputTask;
import cz.mg.compiler.tasks.input.binary.BinaryInputTask;
import cz.mg.compiler.utilities.debug.Text;


public class BinaryFileInputFactory implements BinaryInputFactory {
    @Info
    private final String workingDirectory;

    public BinaryFileInputFactory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    public BinaryInputTask create(Text url) {
        return new BinaryFileInputTask(workingDirectory, url);
    }
}
