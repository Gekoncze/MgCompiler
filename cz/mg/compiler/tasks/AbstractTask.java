package cz.mg.compiler.tasks;

import cz.mg.temp.node.AbstractTreeNode;
import cz.mg.temp.node.TreeNode;


public abstract class AbstractTask<A extends Task, B extends TreeNode> extends AbstractTreeNode<A, B, TreeNode, Object> implements Task<A, B> {
    public AbstractTask(A parent) {
        super(parent);
    }
    
    protected abstract void onRun();
    
    public final void run() {
		try {
            onRun();
		} catch(CompileException e){
			new TaskError(this, e);
		}
	}
}
