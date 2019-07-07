package cz.mg.compiler.tasks.composer;

import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Traceable;


public class ComposeException extends CompileException {
    public ComposeException(Traceable traceable, Object... messageParts) {
        super(traceable, messageParts);
    }

    public ComposeException(Exception e, Traceable traceable, Object... messageParts) {
        super(e, traceable, messageParts);
    }
}
