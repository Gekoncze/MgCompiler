package cz.mg.compiler.tasks.b.composer;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Segment;
import cz.mg.compiler.entities.a.segments.tokens.CommentToken;
import cz.mg.compiler.entities.a.segments.tokens.Token;
import cz.mg.compiler.entities.b.logical.Logical;
import cz.mg.compiler.entities.b.logical.source.LogicalComment;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.TransformTask;
import cz.mg.compiler.tasks.b.composer.source.TokenReader;


public abstract class Composer<A extends Task, B extends Composer, C extends Segment, D extends Logical> extends TransformTask<A, B, C, D> {
    private final ChainList<Token> tokens = new ChainList<>();
    private final ChainList<Line> lines = new ChainList<>();
    
    public Composer(A parentTask, C input, D output) {
        super(parentTask, input, output);
    }

    @Override
    protected final void onRun() {
        for(Object child : getInput()){
            if(child instanceof Token){
                Token token = (Token) child;
                if(token instanceof CommentToken) new LogicalComment(getOutput(), ((CommentToken) token).getLocation(), token.getText());
                else tokens.addLast(token);
            } else if(child instanceof Line){
                Line line = (Line) child;
                if(line.getChildren().count() <= 0) continue;
                Object firstChild = line.getChildren().getFirst();
                if(firstChild instanceof CommentToken) new LogicalComment(getOutput(), ((CommentToken) firstChild).getLocation(), ((CommentToken) firstChild).getText());
                else lines.addLast(line);
            } else {
                throw new RuntimeException();
            }
        }
        if(tokens.count() > 0) onTokensRead(new TokenReader(tokens, ((Line)getInput()).getLocation()));
        if(lines.count() > 0) onLinesRead(lines);
        onReadEnd();
    }
    
    protected abstract void onTokensRead(TokenReader reader);
    protected abstract void onLinesRead(ChainList<Line> lines);
    protected void onReadEnd(){}
}
