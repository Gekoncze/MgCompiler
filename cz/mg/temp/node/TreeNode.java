package cz.mg.temp.node;

import cz.mg.collections.list.chainlist.ChainList;
import java.util.Iterator;


public class TreeNode<A extends TreeNode, B extends TreeNode, C extends TreeNode, D extends Object> extends cz.mg.collections.node.TreeNode<A, B> {
	private final ChainList<C> references = new ChainList<>();
	private final ChainList<D> properties = new ChainList<>();
    
    public TreeNode(A parent) {
        setParent(parent);
	}

    public ChainList<C> getReferences() {
        return references;
    }

    public ChainList<D> getProperties() {
        return properties;
    }
}
