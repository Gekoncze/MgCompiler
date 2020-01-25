package cz.mg.compiler.tasks.output.binary;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.tasks.output.OutputTask;
import java.nio.ByteBuffer;


public abstract class BinaryOutputTask extends OutputTask {
    @Link
    protected final ByteBuffer bytes;

    protected BinaryOutputTask(ByteBuffer bytes) {
        this.bytes = bytes;
    }
}
