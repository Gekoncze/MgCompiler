package cz.mg.compiler.tasks.b.composer.project;

import cz.mg.collections.chainlist.ChainList;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Page;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.b.logical.project.LogicalProjectPage;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import static cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword.PROJECT;
import static cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword.SOURCE;
import cz.mg.compiler.entities.a.segments.tokens.Token;
import cz.mg.compiler.entities.b.logical.project.LogicalProjectName;
import cz.mg.compiler.entities.b.logical.project.LogicalProjectSourceFile;
import cz.mg.compiler.tasks.AbstractTask;
import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.tasks.b.composer.Composer;
import cz.mg.compiler.tasks.b.composer.source.TokenReader;


public class ProjectPageComposer extends Composer<AbstractTask, Composer, Page, LogicalProjectPage> {
    public ProjectPageComposer(AbstractTask parentTask, Page input, LogicalProjectPage output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader reader) {
        throw new RuntimeException();
    }

    @Override
    protected void onLinesRead(ChainList<Line> lines) {
        for(Line line : lines){
            Token first = (Token) line.getChildren().getFirst();
            if(!(first instanceof KeywordToken)) throw new CompileException((Word)first, "Expected keyword.");
            KeywordToken token = (KeywordToken) first;
            switch(token.getKeyword()){
                case PROJECT:
                    new ProjectNameComposer(this, line, new LogicalProjectName(getOutput(), line.getLocation())).run();
                    break;
                    
                case SOURCE:
                    new ProjectSouceFileComposer(this, line, new LogicalProjectSourceFile(getOutput(), line.getLocation())).run();
                    break;
            }
        }
    }
}
