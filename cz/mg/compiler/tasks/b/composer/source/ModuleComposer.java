package cz.mg.compiler.tasks.b.composer.source;

import cz.mg.compiler.entities.a.segments.tokens.OperatorToken;
import cz.mg.collections.chainlist.ChainList;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.entities.b.logical.source.LogicalModule;
import cz.mg.compiler.entities.a.segments.Segment;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import cz.mg.compiler.entities.a.segments.tokens.NameToken;
import cz.mg.compiler.tasks.b.composer.Composer;


public class ModuleComposer extends Composer<SourcePageComposer, Composer, Segment, LogicalModule> {
    public ModuleComposer(SourcePageComposer parentTask, Segment input, LogicalModule output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader reader) {
        LogicalModule logicalModule = getOutput();
		reader.moveNext(KeywordToken.Keyword.MODULE);
		
		ChainList<String> namePath = new ChainList<>();
        NameToken nameToken = (NameToken) reader.moveNext(NameToken.class);
        namePath.addLast(nameToken.getText());
        while(reader.moveNextOptional(OperatorToken.Operator.DOT) != null){
            nameToken = (NameToken) reader.moveNext(NameToken.class);
            namePath.addLast(nameToken.getText());
        }
        reader.readEnd(logicalModule);
        logicalModule.setPath(namePath);
    }

    @Override
    protected void onLinesRead(ChainList<Line> lines) {
        throw new CompileException(lines.getFirst(), "Module specification cannot have children.");
    }
}
