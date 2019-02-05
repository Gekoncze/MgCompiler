package cz.mg.compiler.tasks.b.composer.source;

import cz.mg.temp.readers.ChainReader;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import cz.mg.compiler.entities.a.segments.tokens.Token;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken.Keyword;
import cz.mg.compiler.entities.a.segments.tokens.OperatorToken;
import cz.mg.compiler.entities.a.segments.tokens.OperatorToken.Operator;
import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.entities.a.segments.tokens.CommentToken;
import cz.mg.compiler.entities.b.logical.Logical;
import cz.mg.compiler.entities.b.logical.source.LogicalComment;


public class TokenReader extends ChainReader<Token> {
    private final Location lineLocation;

    public TokenReader(ChainList<Token> list, Location lineLocation) {
        super(list);
        this.lineLocation = lineLocation;
    }
    
    public void readEnd(Logical commentParent) {
        if(hasNext()){
            Token token = moveNext();
            if(token instanceof CommentToken){
                new LogicalComment(commentParent, token.getLocation(), token.getText());
                if(hasNext()) throw new RuntimeException();
            } else {
                throw new CompileException((Word)token, unexpectedTokenMessage(token, null));
            }
        }
    }
    
    public Token moveNext(Class... classes) {
        Token token = moveNext();
        for(Class c : classes){
            if(c.isAssignableFrom(token.getClass())){
                return token;
            }
        }
        throw new CompileException((Word)token, unexpectedTokenMessage(token, tokenClassesToString(classes)));
    }
    
    public Keyword moveNext(Keyword... keywords) {
        Token token = moveNext();
        if(token instanceof KeywordToken){
            Keyword kk = ((KeywordToken) token).getKeyword();
            for(Keyword k : keywords){
                if(kk.equals(k)) return kk;
            }
        }
        throw new CompileException((Word)token, unexpectedTokenMessage(token, keywordsToString(keywords)));
    }
    
    public Keyword moveNextOptional(Keyword... keywords) {
        setMark();
        Token token = moveNextOptional();
        if(token instanceof KeywordToken){
            Keyword kk = ((KeywordToken) token).getKeyword();
            for(Keyword k : keywords){
                if(kk.equals(k)) return kk;
            }
        }
        returnToMark();
        return null;
    }
    
    public Class moveNextOptional(Class... classes) {
        setMark();
        Token token = moveNextOptional();
        for(Class c : classes){
            if(c.equals(token.getClass())) return c;
        }
        returnToMark();
        return null;
    }
	
	public Operator moveNext(Operator... operators) {
        Token token = moveNext();
        if(token instanceof OperatorToken){
            Operator oo = ((OperatorToken) token).getOperator();
            for(Operator o : operators){
                if(oo.equals(o)) return oo;
            }
        }
        throw new CompileException((Word)token, unexpectedTokenMessage(token, operatorsToString(operators)));
    }
    
    public Operator moveNextOptional(Operator... operators) {
        setMark();
        Token token = moveNextOptional();
        if(token instanceof OperatorToken){
            Operator oo = ((OperatorToken) token).getOperator();
            for(Operator o : operators){
                if(oo.equals(o)) return oo;
            }
        }
        returnToMark();
        return null;
    }
    
//    public Character moveNextOptional(char... chars) {
//        setMark();
//        Token token = moveNextOptional();
//        if(token instanceof SpecialCharacterToken){
//            SpecialCharacterToken scht = (SpecialCharacterToken) token;
//            for(char ch : chars){
//                if(scht.getChar() == ch){
//                    return ch;
//                }
//            }
//        }
//        returnToMark();
//        return null;
//    }
    
    private static Object[] unexpectedTokenMessage(Token token, String expectations){
        String e = expectations == null ? "" : " Expected " + expectations + ".";
        return new Object[]{
            "Unexpected token ", token, "(", token.getText(), ")." + e
        };
    }
    
    private static String keywordsToString(Keyword[] keywords){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < keywords.length; i++){
            if(i > 0) s.append(", ");
            s.append(keywords[i].name());
        }
        return s.toString();
    }
	
	private static String operatorsToString(Operator[] operators){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < operators.length; i++){
            if(i > 0) s.append(", ");
            s.append(operators[i].name());
        }
        return s.toString();
    }
    
    private static String tokenClassesToString(Class[] classes){
        String s = "";
        for(int i = 0; i < classes.length; i++){
            if(i > 0) s += ", ";
            try {
                s += classes[i].getSimpleName();
            } catch (Exception e) {
                throw new RuntimeException(e.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
        return s;
    }

    @Override
    protected CompileException outOfBoundsException() {
        return new CompileException(lineLocation, "Missing token.");
    }
}

