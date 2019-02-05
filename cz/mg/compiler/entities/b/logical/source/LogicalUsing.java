package cz.mg.compiler.entities.b.logical.source;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Location;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalUsing extends Logical<LogicalSourcePage, Logical> {
    private ChainList<String> path = new ChainList<>();

    public LogicalUsing(LogicalSourcePage parent, Location location) {
		super(parent, location);
    }

    public ChainList<String> getModulePath() {
        return path;
    }

    public void setModulePath(ChainList<String> modulePath) {
        this.path = modulePath;
    }
}
