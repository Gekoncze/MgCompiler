package cz.mg.compiler.entities.text;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Child;
import cz.mg.compiler.utilities.debug.Text;


public class Line extends TextEntity {
    private int indentation;

    @Child
    private final ChainList<Token> tokens = new CachedChainList<>();

    private Text comment = null;

    public Line(Text content) {
        super(content);
    }

    public int getIndentation() {
        return indentation;
    }

    public void setIndentation(int indentation) {
        this.indentation = indentation;
    }

    public ChainList<Token> getTokens() {
        return tokens;
    }

    public Text getComment() {
        return comment;
    }

    public void setComment(Text comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getContent().toString() + "]";
    }
}
