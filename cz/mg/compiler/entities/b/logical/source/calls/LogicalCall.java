package cz.mg.compiler.entities.b.logical.source.calls;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public abstract class LogicalCall extends Logical<Logical, LogicalCall> {
    public LogicalCall(Location location) {
        super(null, location);
    }
    
    public LogicalCall(Logical parent, Location location) {
        super(parent, location);
    }
}
