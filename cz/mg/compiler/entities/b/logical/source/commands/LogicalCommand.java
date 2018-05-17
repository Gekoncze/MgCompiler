package cz.mg.compiler.entities.b.logical.source.commands;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public abstract class LogicalCommand extends Logical<Logical, Logical> {    
    public LogicalCommand(Logical parent, Location location) {
        super(parent, location);
    }
}
