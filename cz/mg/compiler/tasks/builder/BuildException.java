package cz.mg.compiler.tasks.builder;

import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Traceable;


public class BuildException extends CompileException {
    public BuildException(Traceable traceable, Object... messageParts) {
        super(traceable, messageParts);
    }

    public BuildException(Exception e, Traceable traceable, Object... messageParts) {
        super(e, traceable, messageParts);
    }
}
