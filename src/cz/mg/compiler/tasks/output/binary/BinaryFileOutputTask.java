package cz.mg.compiler.tasks.output.binary;

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


public class BinaryFileOutputTask extends BinaryOutputTask {
    @Info
    private final ReadonlyText absolutePath;

    @Link
    private final ReadonlyText relativePath;

    public BinaryFileOutputTask(ReadonlyText absolutePath, ReadonlyText relativePath, ByteBuffer bytes) {
        super(bytes);
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
    }

    @Override
    protected void onRun() {
        String path = absolutePath.toString() + relativePath.toString();
        File file = new File(path);
        try (FileChannel writer = FileChannel.open(file.toPath(), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            writer.write(bytes);
        } catch (IOException e){
            Traceable traceable = relativePath instanceof Text ? (Traceable) relativePath : new Trace();
            throw new CompileException(e, traceable, "Could not save bytes to file ", absolutePath, relativePath);
        }
    }
}
