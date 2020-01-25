package cz.mg.compiler.tasks.input.binary;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.tasks.input.InputTask;
import java.nio.ByteBuffer;


public abstract class BinaryInputTask extends InputTask {
    @Link
    protected ByteBuffer bytes = null;

    public BinaryInputTask() {
    }

    public ByteBuffer getBytes() {
        return bytes;
    }
}
