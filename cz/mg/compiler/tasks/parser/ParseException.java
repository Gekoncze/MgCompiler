package cz.mg.compiler.tasks.parser;

import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Traceable;


public class ParseException extends CompileException {
    public ParseException(Traceable traceable, Object... messageParts) {
        super(traceable, messageParts);
    }

    public ParseException(Exception e, Traceable traceable, Object... messageParts) {
        super(e, traceable, messageParts);
    }
}
