package cz.mg.compiler.entities.b.logical.source.calls;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalAccessUsage extends Logical {
    private final String name;
    
    public LogicalAccessUsage(Logical parent, Location location, String name) {
        super(parent, location);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
