package cz.mg.compiler.tasks.output.binary;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Text;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;


public class BinaryFileOutputTask extends BinaryOutputTask {
    @Info
    private final String absolutePath;

    @Link
    private final Text relativePath;

    public BinaryFileOutputTask(String absolutePath, Text relativePath, ByteBuffer bytes) {
        super(bytes);
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
    }

    @Override
    protected void onRun() {
        String path = absolutePath + relativePath;
        File file = new File(path);
        try (FileChannel writer = FileChannel.open(file.toPath(), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            writer.write(bytes);
        } catch (IOException e){
            throw new CompileException(e, relativePath, "Could not save bytes to file ", absolutePath, relativePath);
        }
    }
}
