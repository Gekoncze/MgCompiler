package cz.mg.compiler.entities.b.logical.source.calls;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalLiteralCall extends LogicalCall {
    private final String value;

    public LogicalLiteralCall(Location location, String value) {
        super(location);
        this.value = value;
    }
    
    public LogicalLiteralCall(Logical parent, Location location, String value) {
        super(parent, location);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
