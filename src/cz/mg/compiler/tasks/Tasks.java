package cz.mg.compiler.tasks;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;


public class Tasks extends Task {
    @Part
    private final ChainList<Task> mainTasks = new ChainList<>();

    public Tasks() {
    }

    public ChainList<Task> getMainTasks() {
        return mainTasks;
    }

    @Override
    protected void onRun() {
        for(Task task : mainTasks) task.run();
    }
}
