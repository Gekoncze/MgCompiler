package cz.mg.compiler.entities.b.logical;

import cz.mg.collections.node.TreeNode;
import cz.mg.compiler.Location;
import cz.mg.compiler.entities.Entity;


public class Logical<A extends TreeNode, B extends Logical> extends Entity<A, B> {
    public Logical(A parent, Location location) {
        super(parent, location);
    }
}
