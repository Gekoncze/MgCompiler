package cz.mg.compiler.entities.b.logical.source;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalVariableGroup extends Logical {
    private final String name;
    
    public LogicalVariableGroup(Logical parent, Location location) {
        super(parent, location);
        name = null;
    }

    public LogicalVariableGroup(Logical parent, Location location, String name) {
        super(parent, location);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
