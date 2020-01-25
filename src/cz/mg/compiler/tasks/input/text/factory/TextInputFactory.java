package cz.mg.compiler.tasks.input.text.factory;

import cz.mg.compiler.tasks.input.text.TextInputTask;
import cz.mg.compiler.utilities.debug.Text;


public interface TextInputFactory {
    TextInputTask create(Text url);
}
