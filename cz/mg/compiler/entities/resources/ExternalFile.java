package cz.mg.compiler.entities.resources;

import cz.mg.temp.node.TreeNode;
import cz.mg.compiler.Location;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ExternalFile extends Stream {
    public ExternalFile(TreeNode parent, Location location, String name) {
        super(parent, location);
        setName(name);
    }
    
    @Override
    public InputStream getStream() throws IOException {
        return new FileInputStream(new File(getName()));
    }
}
