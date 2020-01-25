package cz.mg.compiler.tasks.input.binary;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;
import cz.mg.compiler.utilities.debug.Traceable;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;


public class BinaryFileInputTask extends BinaryInputTask {
    @Info
    private final ReadonlyText absolutePath;

    @Link
    private final ReadonlyText relativePath;

    public BinaryFileInputTask(ReadonlyText absolutePath, ReadonlyText relativePath) {
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
    }

    @Override
    protected void onRun() {
        String path = absolutePath.toString() + relativePath.toString();
        File file = new File(path);
        try (FileChannel reader = FileChannel.open(file.toPath(), StandardOpenOption.READ)) {
            bytes = ByteBuffer.allocateDirect((int) file.length());
            reader.read(bytes);
        } catch (IOException e){
            Traceable traceable = relativePath instanceof Text ? (Traceable) relativePath : new Trace();
            throw new CompileException(e, traceable, "Could not load bytes from file ", absolutePath, relativePath);
        }
    }
}
