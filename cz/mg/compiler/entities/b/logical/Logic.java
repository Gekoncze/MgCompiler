package cz.mg.compiler.entities.b.logical;

import cz.mg.temp.node.TreeNode;
import cz.mg.compiler.Location;


public class Logic extends Logical<TreeNode, Logical> {
    public Logic(TreeNode parent, Location location) {
        super(parent, location);
    }
}
