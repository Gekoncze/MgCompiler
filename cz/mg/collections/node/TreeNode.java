package cz.mg.collections.node;

import cz.mg.collections.chainlist.ChainList;


public interface TreeNode<A extends TreeNode, B extends TreeNode, C extends TreeNode, D extends Object> extends Iterable<B> {
	public A getParent();
    public void setParent(A parent);
    public ChainList<B> getChildren();
    public ChainList<C> getReferences();
    public ChainList<D> getProperties();
}
