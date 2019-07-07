package cz.mg.compiler.entities.text;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Child;
import cz.mg.compiler.utilities.debug.Text;


public class Page extends TextEntity {
    @Child
    private final ChainList<Line> lines = new CachedChainList<>();

    public Page(Text content) {
        super(content);
    }

    public ChainList<Line> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getTrace().getFilename() + "]";
    }
}
