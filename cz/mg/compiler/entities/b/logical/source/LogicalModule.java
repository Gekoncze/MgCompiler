package cz.mg.compiler.entities.b.logical.source;

import cz.mg.collections.chainlist.ChainList;
import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalModule extends Logical<LogicalSourcePage, Logical> {
    private ChainList<String> path;
    
    public LogicalModule(LogicalSourcePage parent, Location location) {
        super(parent, location);
    }
    
    public ChainList<String> getPath() {
        return path;
    }
    
    public void setPath(ChainList<String> path) {
        this.path = path;
    } 
}
