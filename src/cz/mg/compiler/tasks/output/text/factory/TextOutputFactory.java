package cz.mg.compiler.tasks.output.text.factory;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.tasks.output.text.TextOutputTask;


public interface TextOutputFactory {
    TextOutputTask create(ReadonlyText url, ReadonlyText text);
}
