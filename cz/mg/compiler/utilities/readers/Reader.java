package cz.mg.compiler.utilities.readers;

import cz.mg.compiler.utilities.debug.CompileException;


public abstract class Reader<T> {
    public abstract T read();
    public abstract T readOptional();
    public abstract void back();
    public abstract boolean canRead();
    protected abstract CompileException outOfBoundsException();
}