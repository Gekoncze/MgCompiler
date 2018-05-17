package cz.mg.compiler.entities.resources;

import cz.mg.collections.node.TreeNode;
import cz.mg.compiler.Location;
import java.io.InputStream;


public abstract class Stream extends Resource {
    private String name;
    
    public Stream(TreeNode parent, Location location) {
        super(parent, location);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public abstract InputStream getStream() throws Exception;
}
