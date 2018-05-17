package cz.mg.compiler.entities;

import cz.mg.collections.node.AbstractTreeNode;
import cz.mg.collections.node.TreeNode;
import cz.mg.compiler.Location;


public abstract class Entity<A extends TreeNode, B extends Entity> extends AbstractTreeNode<A, B, TreeNode, Object> {
    public Entity(A parent, Location location) {
        super(parent);
        getProperties().addLast(location);
    }
    
    public Location getLocation(){
        for(Object property : getProperties()){
            if(property instanceof Location) return (Location) property;
        }
        return null;
    }
}
