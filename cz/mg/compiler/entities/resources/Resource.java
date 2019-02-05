package cz.mg.compiler.entities.resources;

import cz.mg.temp.node.TreeNode;
import cz.mg.compiler.Location;
import cz.mg.compiler.entities.Entity;


public abstract class Resource extends Entity<TreeNode, Resource> {
    public Resource(TreeNode parent, Location location) {
        super(parent, location);
    }
}
