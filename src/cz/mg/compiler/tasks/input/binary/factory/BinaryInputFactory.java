package cz.mg.compiler.tasks.input.binary.factory;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.tasks.input.binary.BinaryInputTask;


public interface BinaryInputFactory {
    BinaryInputTask create(ReadonlyText url);
}
