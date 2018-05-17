package cz.mg.compiler.entities.b.logical.source.calls;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalMemberAccessCall extends LogicalCall {
    public LogicalMemberAccessCall(Location location) {
        super(location);
    }
    
    public LogicalMemberAccessCall(Logical parent, Location location) {
        super(parent, location);
    }
}
