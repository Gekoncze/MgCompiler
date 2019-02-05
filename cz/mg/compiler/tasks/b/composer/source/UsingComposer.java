package cz.mg.compiler.tasks.b.composer.source;

import cz.mg.compiler.entities.a.segments.tokens.OperatorToken;
import cz.mg.compiler.entities.b.logical.source.LogicalUsing;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.a.segments.Line;
import static cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword.USING;
import cz.mg.compiler.entities.a.segments.tokens.NameToken;
import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.tasks.b.composer.Composer;


public class UsingComposer extends Composer<SourcePageComposer, Composer, Line, LogicalUsing> {
    public UsingComposer(SourcePageComposer parentTask, Line input, LogicalUsing output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader reader) {
        LogicalUsing logicalUsing = getOutput();
        ChainList<String> namePath = new ChainList<>();
        
        reader.moveNext(USING);
        
        NameToken nameToken = (NameToken) reader.moveNext(NameToken.class);
        namePath.addLast(nameToken.getText());
        
        while(reader.moveNextOptional(OperatorToken.Operator.DOT) != null){
            nameToken = (NameToken) reader.moveNext(NameToken.class);
            namePath.addLast(nameToken.getText());
        }
        
        reader.readEnd(logicalUsing);
        logicalUsing.setModulePath(namePath);
    }

    @Override
    protected void onLinesRead(ChainList<Line> lines) {
        throw new CompileException(lines.getFirst(), "Using cannot have children.");
    }
}
