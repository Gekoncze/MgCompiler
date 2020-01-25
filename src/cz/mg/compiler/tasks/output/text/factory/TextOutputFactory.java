package cz.mg.compiler.tasks.output.text.factory;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.tasks.output.text.TextOutputTask;
import cz.mg.compiler.utilities.debug.Text;


public interface TextOutputFactory {
    TextOutputTask create(Text url, ReadonlyText text);
}
