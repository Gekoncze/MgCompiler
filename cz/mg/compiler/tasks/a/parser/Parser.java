package cz.mg.compiler.tasks.a.parser;

import cz.mg.compiler.entities.a.segments.Segment;
import cz.mg.compiler.tasks.TransformTask;
import cz.mg.compiler.tasks.Task;


public abstract class Parser<A extends Task, B extends Parser, C extends Object, D extends Segment> extends TransformTask<A, B, C, D> {
    public Parser(A parentTask, C input, D output) {
        super(parentTask, input, output);
    }
}
