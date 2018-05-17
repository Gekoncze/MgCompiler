package cz.mg.compiler.tasks.a.parser;

import cz.mg.compiler.entities.a.segments.Segment;
import cz.mg.compiler.tasks.AbstractTransformTask;
import cz.mg.compiler.tasks.Task;


public abstract class AbstractParser<A extends Task, B extends Parser, C extends Object, D extends Segment> extends AbstractTransformTask<A, B, C, D> implements Parser<A, B, C, D> {
    public AbstractParser(A parentTask, C input, D output) {
        super(parentTask, input, output);
    }
}
