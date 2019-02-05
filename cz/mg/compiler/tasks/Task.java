package cz.mg.compiler.tasks;

import cz.mg.temp.node.TreeNode;


public interface Task<A extends Task, B extends TreeNode> extends TreeNode<A, B, TreeNode, Object> {
	public void run();
    
    public default boolean hasErrors() {
        for(Object child : getChildren()){
            if(child instanceof TaskError) return true;
            if(child instanceof Task) if(((Task) child).hasErrors()) return true;
        }
        return false;
    }
}
