package cz.mg.compiler.tasks.output.binary.factory;

import cz.mg.compiler.tasks.output.binary.BinaryOutputTask;
import cz.mg.compiler.utilities.debug.Text;
import java.nio.ByteBuffer;


public interface BinaryOutputFactory {
    BinaryOutputTask create(Text url, ByteBuffer bytes);
}
