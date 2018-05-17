package cz.mg.compiler.entities.a.segments.tokens;


public interface KeywordToken extends Token {
	public Keyword getKeyword();
	
	public static enum Keyword {
        AND,
        AS,
        BREAK,
        CLASS,
        CONTINUE,
        DEFINE,
		ELSE,
		FALSE,
        FILE,
        FUNCTION,
        IF,
        INPUT,
		IS,
        MODULE,
        NAND,
        NOR,
        NOT,
        NULL,
		OPERATOR,
        OR,
        OUTPUT,
        PRIVATE,
        PROJECT,
        PUBLIC,
        RETURN,
        SOURCE,
		TRUE,
        USING,
		VOID,
        WHILE,
        XNOR,
        XOR;
		
		public static Keyword fromString(String value){
			try {
				return Keyword.valueOf(value);
			} catch(IllegalArgumentException e){
				return null;
			}
		}
    }
}
