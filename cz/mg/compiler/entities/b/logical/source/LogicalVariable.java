package cz.mg.compiler.entities.b.logical.source;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalVariable extends Logical {
    private String name;

    public LogicalVariable(Logical parent, Location location) {
        super(parent, location);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
