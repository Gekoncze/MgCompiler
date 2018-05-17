package cz.mg.compiler.entities.b.logical.source.calls;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalAssignmentCall extends LogicalCall {
    public LogicalAssignmentCall(Location location) {
        super(location);
    }
    
    public LogicalAssignmentCall(Logical parent, Location location) {
        super(parent, location);
    }
}
