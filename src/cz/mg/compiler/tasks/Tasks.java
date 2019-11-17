package cz.mg.compiler.tasks;


public class Tasks extends Task {
    public Tasks() {
        super(null);
    }

    @Override
    protected void onRun() {
        for(Task task : getTasks()) task.run();
    }
}
