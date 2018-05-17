package cz.mg.compiler.entities.a.segments;

import cz.mg.collections.node.TreeNode;
import cz.mg.compiler.Location;
import cz.mg.compiler.entities.Entity;


public abstract class Segment<A extends TreeNode, B extends Segment> extends Entity<A, B> {
    public Segment(A parent, Location location) {
        super(parent, location);
    }
}
