package cz.mg.compiler.tasks.b.composer.project;

import cz.mg.collections.chainlist.ChainList;
import cz.mg.compiler.entities.a.segments.Line;
import static cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword.PROJECT;
import cz.mg.compiler.entities.a.segments.tokens.LiteralToken;
import cz.mg.compiler.entities.b.logical.project.LogicalProjectName;
import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.tasks.b.composer.Composer;
import cz.mg.compiler.tasks.b.composer.source.TokenReader;


public class ProjectNameComposer extends Composer<Composer, Composer, Line, LogicalProjectName> {
    public ProjectNameComposer(Composer parentTask, Line input, LogicalProjectName output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader tokenReader) {
        tokenReader.moveNext(PROJECT);
        LiteralToken literalToken = (LiteralToken) tokenReader.moveNext(LiteralToken.class);
        getOutput().setName(literalToken.getText());
    }

    @Override
    protected void onLinesRead(ChainList<Line> lines) {
        throw new CompileException(lines.getFirst(), "Project name cannot have children.");
    }
}
