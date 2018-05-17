package cz.mg.collections.node;

import cz.mg.collections.chainlist.ChainList;
import java.util.Iterator;


public class AbstractTreeNode<A extends TreeNode, B extends TreeNode, C extends TreeNode, D extends Object> implements TreeNode<A, B, C, D> {
    private A parent = null;
	private final ChainList<B> children = new ChainList<>();
	private final ChainList<C> references = new ChainList<>();
	private final ChainList<D> properties = new ChainList<>();
    
    public AbstractTreeNode(A parent) {
        setParent(parent);
	}
    
    public final A getParent() {
		return parent;
	}

    public void setParent(A parent) {
        if(this.parent != null) throw new RuntimeException();
        this.parent = parent;
        if(parent != null) parent.getChildren().addLast(this);
    }

    @Override
    public ChainList<B> getChildren() {
        return children;
    }

    @Override
    public ChainList<C> getReferences() {
        return references;
    }

    @Override
    public ChainList<D> getProperties() {
        return properties;
    }

	@Override
	public Iterator<B> iterator() {
		return children.iterator();
	}    
}
