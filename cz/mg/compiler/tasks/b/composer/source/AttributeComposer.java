package cz.mg.compiler.tasks.b.composer.source;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.entities.b.logical.source.LogicalAttribute;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import cz.mg.compiler.entities.a.segments.tokens.NameToken;
import cz.mg.compiler.entities.a.segments.tokens.Token;
import cz.mg.compiler.entities.a.segments.tokens.TypeToken;
import cz.mg.compiler.entities.b.logical.source.LogicalType;
import cz.mg.compiler.entities.b.logical.source.calls.LogicalAccessUsage;
import cz.mg.compiler.tasks.b.composer.Composer;


public class AttributeComposer extends Composer<ClassComposer, Composer, Line, LogicalAttribute> {
    public AttributeComposer(ClassComposer parentTask, Line input, LogicalAttribute output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader reader) {
        LogicalAttribute attribute = getOutput();
        
        Token accessToken = reader.moveNext(NameToken.class, KeywordToken.class);
		new LogicalAccessUsage(attribute, accessToken.getLocation(), accessToken.getText());
        
        TypeToken typeToken = (TypeToken) reader.moveNext(TypeToken.class);
        new LogicalType(attribute, typeToken.getLocation(), typeToken.getText(), typeToken.getPointerCount());
        
        attribute.setName(reader.moveNext(NameToken.class).getText());
    }

    @Override
    protected void onLinesRead(ChainList<Line> lines) {
        throw new CompileException(lines.getFirst(), "Attribute cannot have children.");
    }
}
