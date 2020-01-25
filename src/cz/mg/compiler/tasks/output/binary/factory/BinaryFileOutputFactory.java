package cz.mg.compiler.tasks.output.binary.factory;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.tasks.output.binary.BinaryFileOutputTask;
import cz.mg.compiler.tasks.output.binary.BinaryOutputTask;
import cz.mg.compiler.utilities.debug.Text;
import java.nio.ByteBuffer;


public class BinaryFileOutputFactory implements BinaryOutputFactory {
    @Info
    private final String workingDirectory;

    public BinaryFileOutputFactory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    public BinaryOutputTask create(Text url, ByteBuffer bytes) {
        return new BinaryFileOutputTask(workingDirectory, url, bytes);
    }
}
