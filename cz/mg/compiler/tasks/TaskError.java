package cz.mg.compiler.tasks;

import cz.mg.temp.node.AbstractTreeNode;
import cz.mg.temp.node.TreeNode;


public class TaskError extends AbstractTreeNode<Task, TreeNode, TreeNode, Object> {
    private final CompileException exception;
    
    public TaskError(Task parent, CompileException exception) {
        super(parent);
        this.exception = exception;
    }

    public CompileException getException() {
        return exception;
    }

    @Override
    public String toString() {
        return exception.toString();
    }
}
