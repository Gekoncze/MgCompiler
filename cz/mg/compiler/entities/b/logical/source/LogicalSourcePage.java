package cz.mg.compiler.entities.b.logical.source;

import cz.mg.temp.node.TreeNode;
import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalSourcePage extends Logical<TreeNode, Logical> {
    public LogicalSourcePage(TreeNode parent, Location location) {
        super(parent, location);
    }
}
