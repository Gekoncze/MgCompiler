package cz.mg.compiler.tasks;

import cz.mg.temp.node.TreeNode;


public abstract class Task<A extends Task, B extends TreeNode> extends TreeNode<A, B, TreeNode, Object> {
    public Task(A parent) {
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

    public boolean hasErrors() {
        for(Object child : getChildren()){
            if(child instanceof TaskError) return true;
            if(child instanceof Task) if(((Task) child).hasErrors()) return true;
        }
        return false;
    }
}
