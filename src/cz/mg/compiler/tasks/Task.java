package cz.mg.compiler.tasks;

import cz.mg.collections.list.List;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.compiler.Element;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.utilities.debug.CompileException;


public abstract class Task extends Element {
    @Info
    private final List<CompileException> errors = new CachedChainList<>();

    public Task() {
    }

    public final List<CompileException> getErrors() {
        return errors;
    }

    public final void run() {
        try {
            onRun();
        } catch(CompileException e) {
            if(!e.isConsumed()) errors.addLast(e);
            e.consume();
            throw e;
        }
    }

    public final void tryToRun() {
        try {
            onRun();
        } catch(CompileException e) {
            if(!e.isConsumed()) errors.addLast(e);
            e.consume();
        }
    }

    protected abstract void onRun();
}
