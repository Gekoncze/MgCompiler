package cz.mg.compiler.entities.structured;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.structured.parts.Stamp;


public class Block extends StructuredEntity {
    @Child
    private final ChainList<Text> documentation;

    @Child
    private final ChainList<Stamp> stamps;

    @Child
    private final ChainList<Part> parts;

    @Child
    private final ChainList<Block> blocks = new CachedChainList<>();

    public Block(Text content, ChainList<Part> parts, ChainList<Text> documentation, ChainList<Stamp> stamps) {
        super(content);
        this.parts = parts;
        this.documentation = documentation;
        this.stamps = stamps;
    }

    public ChainList<Part> getParts() {
        return parts;
    }

    public ChainList<Text> getDocumentation() {
        return documentation;
    }

    public ChainList<Stamp> getStamps() {
        return stamps;
    }

    public ChainList<Block> getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + toStringDetails() + "]";
    }

    protected String toStringDetails(){
        return getContent().toString();
    }
}
