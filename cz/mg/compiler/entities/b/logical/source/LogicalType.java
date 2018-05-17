package cz.mg.compiler.entities.b.logical.source;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalType extends Logical {
    private final String typename;
    private final int pointerCount;

    public LogicalType(Logical parent, Location location, String typename, int pointerCount) {
        super(parent, location);
        this.typename = typename;
        this.pointerCount = pointerCount;
    }

    public String getTypename() {
        return typename;
    }

    public int getPointerCount() {
        return pointerCount;
    }
    
    public boolean matches(LogicalType type) {
        if(type.typename.equals("NULL") && type.pointerCount > 0) return true;
        if(type.pointerCount != pointerCount) return false;
        return typename.matches(type.typename);
    }
}
