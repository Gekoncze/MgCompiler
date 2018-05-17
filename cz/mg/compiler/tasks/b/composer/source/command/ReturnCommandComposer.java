package cz.mg.compiler.tasks.b.composer.source.command;

import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword;
import cz.mg.compiler.entities.b.logical.Logical;
import cz.mg.compiler.tasks.b.composer.Composer;
import cz.mg.compiler.tasks.b.composer.source.TokenReader;


public class ReturnCommandComposer extends ExpressionCommandComposer {
    public ReturnCommandComposer(Composer parentTask, Line input, Logical output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader reader) {
        reader.moveNext(Keyword.RETURN);
        super.onTokensRead(reader);
    }
}
