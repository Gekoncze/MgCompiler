package cz.mg.compiler.entities.resources;

import cz.mg.collections.node.TreeNode;
import cz.mg.compiler.Location;
import java.io.IOException;
import java.io.InputStream;
import samples.SamplesLocation;


public class InternalFile extends Stream {
    public InternalFile(TreeNode parent, Location location, String name) {
        super(parent, location);
        setName(name);
    }

    @Override
    public InputStream getStream() throws IOException {
        InputStream stream = SamplesLocation.class.getResourceAsStream(getName().toString());
        if(stream == null) throw new IOException("Could not find " + getName().toString() + " in " + SamplesLocation.class.getPackage().getName());
        return stream;
    }
}
