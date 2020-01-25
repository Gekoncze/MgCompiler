package cz.mg.compiler.tasks.input.binary.factory;

import cz.mg.compiler.tasks.input.binary.BinaryInputTask;
import cz.mg.compiler.utilities.debug.Text;


public interface BinaryInputFactory {
    BinaryInputTask create(Text url);
}
