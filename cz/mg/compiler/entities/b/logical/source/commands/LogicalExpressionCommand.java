package cz.mg.compiler.entities.b.logical.source.commands;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalExpressionCommand extends LogicalCommand {
    public LogicalExpressionCommand(Logical parent, Location location) {
        super(parent, location);
    }
}
