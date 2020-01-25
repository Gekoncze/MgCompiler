package cz.mg.compiler.tasks.output.binary.factory;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.tasks.output.binary.BinaryFileOutputTask;
import cz.mg.compiler.tasks.output.binary.BinaryOutputTask;
import java.nio.ByteBuffer;


public class BinaryFileOutputFactory implements BinaryOutputFactory {
    @Info
    private final ReadonlyText workingDirectory;

    public BinaryFileOutputFactory(ReadonlyText workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    public BinaryOutputTask create(ReadonlyText url, ByteBuffer bytes) {
        return new BinaryFileOutputTask(workingDirectory, url, bytes);
    }
}
