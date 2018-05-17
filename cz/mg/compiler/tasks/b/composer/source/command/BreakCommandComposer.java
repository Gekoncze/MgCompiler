package cz.mg.compiler.tasks.b.composer.source.command;

import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import cz.mg.compiler.entities.b.logical.Logical;
import cz.mg.compiler.tasks.b.composer.Composer;
import cz.mg.compiler.tasks.b.composer.source.TokenReader;


public class BreakCommandComposer extends ExpressionCommandComposer {
    public BreakCommandComposer(Composer parentTask, Line input, Logical output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader reader) {
        reader.moveNext(KeywordToken.Keyword.BREAK);
        reader.readEnd(getOutput());
    }
}
