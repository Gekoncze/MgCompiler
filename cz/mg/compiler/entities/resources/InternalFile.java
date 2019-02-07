package cz.mg.compiler.entities.resources;

import cz.mg.temp.node.TreeNode;
import cz.mg.compiler.Location;

import java.io.IOException;
import java.io.InputStream;


public class InternalFile extends Stream {
    private final InputStream stream;

    public InternalFile(TreeNode parent, Location location, String name, InputStream stream) {
        super(parent, location);
        setName(name);
        this.stream = stream;
    }

    @Override
    public InputStream getStream() throws IOException {
        return stream;
    }
}
