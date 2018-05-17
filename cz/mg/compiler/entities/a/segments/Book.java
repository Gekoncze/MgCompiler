package cz.mg.compiler.entities.a.segments;

import cz.mg.collections.node.TreeNode;
import cz.mg.compiler.Location;


public class Book extends Segment<TreeNode, Page> {
    public Book(TreeNode parent, Location location) {
        super(parent, location);
    }
}
