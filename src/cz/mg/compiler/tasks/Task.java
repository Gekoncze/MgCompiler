package cz.mg.compiler.tasks;

import cz.mg.collections.list.List;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.compiler.Element;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.utilities.debug.CompileException;


public abstract class Task extends Element {
    private final Task parentTask;

    @Child
    private final List<Task> tasks = new CachedChainList<>();
    private final List<CompileException> errors = new CachedChainList<>();

    public Task(Task parentTask) {
        this.parentTask = parentTask;
        if(parentTask != null) parentTask.tasks.addLast(this);
    }

    public final Task getParentTask() {
        return parentTask;
    }

    public final List<Task> getTasks() {
        return tasks;
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

    public final List<CompileException> getAllErrors(){
        List<CompileException> allErrors = new CachedChainList<>(getErrors());
        for(Task task : tasks) allErrors.addCollectionLast(task.getAllErrors());
        return allErrors;
    }

    protected abstract void onRun();
}
