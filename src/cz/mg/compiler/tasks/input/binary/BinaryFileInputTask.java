package cz.mg.compiler.tasks.input.binary;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Text;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;


public class BinaryFileInputTask extends BinaryInputTask {
    @Info
    private final String absolutePath;

    @Link
    private final Text relativePath;

    public BinaryFileInputTask(String absolutePath, Text relativePath) {
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
    }

    @Override
    protected void onRun() {
        String path = absolutePath + relativePath;
        File file = new File(path);
        try (FileChannel reader = FileChannel.open(file.toPath(), StandardOpenOption.READ)) {
            bytes = ByteBuffer.allocateDirect((int) file.length());
            reader.read(bytes);
        } catch (IOException e){
            throw new CompileException(e, relativePath, "Could not load bytes from file ", absolutePath, relativePath);
        }
    }
}
