package cz.mg.compiler.tasks.output.binary.factory;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.tasks.output.binary.BinaryOutputTask;
import java.nio.ByteBuffer;


public interface BinaryOutputFactory {
    BinaryOutputTask create(ReadonlyText url, ByteBuffer bytes);
}
