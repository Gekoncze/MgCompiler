package cz.mg.compiler.entities.b.logical.source.calls;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalNameCall extends LogicalCall {
    private final String name;
    
    public LogicalNameCall(Location location, String name) {
        super(location);
        this.name = name;
    }

    public LogicalNameCall(Logical parent, Location location, String name) {
        super(parent, location);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
