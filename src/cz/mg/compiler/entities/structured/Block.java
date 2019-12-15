package cz.mg.compiler.entities.structured;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.structured.parts.Stamp;


public class Block extends StructuredEntity {
    @Part
    private final ChainList<Text> documentation;

    @Part
    private final ChainList<Stamp> stamps;

    @Part
    private final ChainList<cz.mg.compiler.entities.structured.Part> parts;

    @Part
    private final ChainList<Block> blocks = new CachedChainList<>();

    public Block(Text content, ChainList<cz.mg.compiler.entities.structured.Part> parts, ChainList<Text> documentation, ChainList<Stamp> stamps) {
        super(content);
        this.parts = parts;
        this.documentation = documentation;
        this.stamps = stamps;
    }

    public ChainList<cz.mg.compiler.entities.structured.Part> getParts() {
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
