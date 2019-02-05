package cz.mg.compiler.entities.b.logical.project;

import cz.mg.temp.node.TreeNode;
import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalProjectPage extends Logical {
    private String name = null;
    
    public LogicalProjectPage(TreeNode parent, Location location) {
        super(parent, location);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
