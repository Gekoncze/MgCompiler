package cz.mg.compiler.entities.b.logical.project;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalProjectSourceFile extends Logical {
    private String name;
    
    public LogicalProjectSourceFile(Logical parent, Location location) {
        super(parent, location);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
