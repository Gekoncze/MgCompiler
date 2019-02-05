package cz.mg.compiler.tasks.b.composer.project;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.a.segments.Line;
import static cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword.FILE;
import static cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword.SOURCE;
import cz.mg.compiler.entities.a.segments.tokens.LiteralToken;
import cz.mg.compiler.entities.b.logical.project.LogicalProjectSourceFile;
import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.tasks.b.composer.Composer;
import cz.mg.compiler.tasks.b.composer.source.TokenReader;


public class ProjectSouceFileComposer extends Composer<Composer, Composer, Line, LogicalProjectSourceFile> {
    public ProjectSouceFileComposer(Composer parentTask, Line input, LogicalProjectSourceFile output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader tokenReader) {
        tokenReader.moveNext(SOURCE);
        tokenReader.moveNext(FILE);
        LiteralToken literalToken = (LiteralToken) tokenReader.moveNext(LiteralToken.class);
        getOutput().setName(literalToken.getText());
    }

    @Override
    protected void onLinesRead(ChainList<Line> lines) {
        throw new CompileException(lines.getFirst(), "Source file path cannot have children.");
    }
}
