package cz.mg.compiler.tasks.b.composer.source;

import cz.mg.collections.chainlist.ChainList;
import cz.mg.compiler.entities.b.logical.source.LogicalClass;
import static cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword.DEFINE;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import static cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword.CLASS;
import cz.mg.compiler.entities.a.segments.tokens.NameToken;
import cz.mg.compiler.entities.a.segments.tokens.Token;
import cz.mg.compiler.entities.a.segments.tokens.TypenameToken;
import cz.mg.compiler.entities.b.logical.source.LogicalAttribute;
import cz.mg.compiler.entities.b.logical.source.LogicalFunction;
import cz.mg.compiler.entities.b.logical.source.calls.LogicalAccessUsage;
import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.tasks.b.composer.Composer;


public class ClassComposer extends Composer<SourcePageComposer, Composer, Line, LogicalClass> {
    public ClassComposer(SourcePageComposer parentTask, Line input, LogicalClass output) {
        super(parentTask, input, output);
    }

    @Override
    protected void onTokensRead(TokenReader reader) {
        LogicalClass logicalClass = getOutput();
        
        reader.moveNext(DEFINE);
        
        Token accessToken = reader.moveNext(NameToken.class, KeywordToken.class);
		new LogicalAccessUsage(logicalClass, accessToken.getLocation(), accessToken.getText());
        
        reader.moveNext(CLASS);
        
        TypenameToken typenameToken = (TypenameToken) reader.moveNext(TypenameToken.class);
        logicalClass.setName(typenameToken.getText());
        
        reader.readEnd(logicalClass);
    }

    @Override
    protected void onLinesRead(ChainList<Line> lines) {
        for(Line line : lines){
            if(Rules.ATTRIBUTE.applicable(line)){
                AttributeComposer attributeComposer = new AttributeComposer(this, line, new LogicalAttribute(getOutput(), line.getLocation()));
                attributeComposer.run();
            } else if(Rules.FUNCTION.applicable(line)){
                FunctionComposer functionComposer = new FunctionComposer(this, line, new LogicalFunction(getOutput(), line.getLocation()));
                functionComposer.run();
            } else {
                throw new CompileException(line, "No rule found for line.");
            }
        }
    }
}
