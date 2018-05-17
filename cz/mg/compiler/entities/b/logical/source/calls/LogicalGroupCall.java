package cz.mg.compiler.entities.b.logical.source.calls;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalGroupCall extends LogicalCall {
    public LogicalGroupCall(Location location) {
        super(location);
    }
    
    public LogicalGroupCall(Logical parent, Location location) {
        super(parent, location);
    }
}
