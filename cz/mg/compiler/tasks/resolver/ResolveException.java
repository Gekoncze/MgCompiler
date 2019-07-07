package cz.mg.compiler.tasks.resolver;

import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Traceable;


public class ResolveException extends CompileException {
    public ResolveException(Traceable traceable, Object... messageParts) {
        super(traceable, messageParts);
    }

    public ResolveException(Exception e, Traceable traceable, Object... messageParts) {
        super(e, traceable, messageParts);
    }
}
