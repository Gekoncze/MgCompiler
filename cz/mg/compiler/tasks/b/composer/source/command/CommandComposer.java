package cz.mg.compiler.tasks.b.composer.source.command;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import cz.mg.compiler.entities.a.segments.tokens.Token;
import cz.mg.compiler.entities.b.logical.Logical;
import cz.mg.compiler.tasks.b.composer.Composer;


public abstract class CommandComposer extends Composer<Composer, Composer, Line, Logical> {
    public CommandComposer(Composer parentTask, Line input, Logical output) {
        super(parentTask, input, output);
    }
    
    @Override
    protected void onLinesRead(ChainList<Line> lines) {
        for(Line line : lines){
            Token token = (Token) line.getChildren().getFirst();
            if(token instanceof KeywordToken){
                KeywordToken.Keyword keyword = ((KeywordToken) token).getKeyword();
                switch(keyword){
                    case IF:
                        new IfCommandComposer(this, line, getOutput()).run();
                        continue;
                    case ELSE:
                        if(line.getChildren().count() > 1){
                            if(line.getChildren().get(1) instanceof KeywordToken){
                                KeywordToken k = (KeywordToken) line.getChildren().get(1);
                                switch(k.getKeyword()){
                                    case IF:
                                        new ElseIfCommandComposer(this, line, getOutput()).run();
                                        continue;
                                }
                            }
                        }
                        new ElseCommandComposer(this, line, getOutput()).run();
                        continue;
                    case WHILE:
                        new WhileCommandComposer(this, line, getOutput()).run();
                        continue;
                    case RETURN:
                        new ReturnCommandComposer(this, line, getOutput()).run();
                        continue;
                    case CONTINUE:
                        new ContinueCommandComposer(this, line, getOutput()).run();
                        continue;
                    case BREAK:
                        new BreakCommandComposer(this, line, getOutput()).run();
                        continue;
                }
            }
            new ExpressionCommandComposer(this, line, getOutput()).run();
        }
    }
}
