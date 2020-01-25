package cz.mg.compiler.tasks.input.text.factory;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.tasks.input.text.TextInputTask;


public interface TextInputFactory {
    TextInputTask create(ReadonlyText url);
}
