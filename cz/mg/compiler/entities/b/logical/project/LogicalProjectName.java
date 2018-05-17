package cz.mg.compiler.entities.b.logical.project;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalProjectName extends Logical {
    private String name;
    
    public LogicalProjectName(Logical parent, Location location) {
        super(parent, location);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
