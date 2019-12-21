package cz.mg.compiler.tasks;

import cz.mg.collections.list.chainlist.ChainList;


public class Tasks extends Task {
    private final ChainList<Task> tasks = new ChainList<>();

    public Tasks() {
    }

    public ChainList<Task> getTasks() {
        return tasks;
    }

    @Override
    protected void onRun() {
        for(Task task : tasks) task.run();
    }
}
