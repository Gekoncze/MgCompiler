package cz.mg.compiler.tasks;

import cz.mg.compiler.entities.Entity;


public abstract class AbstractTransformTask<A extends Task, B extends Task, C extends Object, D extends Entity> extends AbstractTask<A, B> implements TransformTask<A, B, C, D> {
    private final C input;
    private final D output;

    public AbstractTransformTask(A parentTask, C input, D output) {
        super(parentTask);
        this.input = input;
        this.output = output;
    }
    
    public C getInput(){
        return input;
    }
    
    public D getOutput(){
        return output;
    }
}
