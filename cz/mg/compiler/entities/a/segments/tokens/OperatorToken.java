package cz.mg.compiler.entities.a.segments.tokens;


public interface OperatorToken extends Token {
	public Operator getOperator();
	
	public static enum Operator {
		INCREMENT,
		DECREMENT,
        DEREFERENCE,
		DOT,
        ADDRESS,
        FACTORIAL,
        POWER,
        MULTIPLY,
        INTEGER_DIVIDE,
        MODULO,
        REAL_DIVIDE,
        PLUS,
        MINUS,
        BITSHIFT_LEFT,
        BITSHIFT_RIGHT,
        AND,
        NAND,
        XOR,
        XNOR,
        OR,
        NOR,
        SMALLER,
        LARGER,
        SMALLER_OR_EQUAL,
        LARGER_OR_EQUAL,
		EQUAL,
		NOT,
        AS,
		IS,
        COMMA,
		COLON,
        ASSIGNMENT;
		
		public static Operator fromString(String value){
			switch(value){
				case "+": return PLUS;
				case "-": return MINUS;
				case "++": return INCREMENT;
				case "--": return DECREMENT;
				case ".": return DOT;
				case "&": return ADDRESS;
				case "@": return DEREFERENCE;
				case "!": return FACTORIAL;
				case "^": return POWER;
				case "*": return MULTIPLY;
				case "\\": return INTEGER_DIVIDE;
				case "%": return MODULO;
				case "/": return REAL_DIVIDE;
				case "<<": return BITSHIFT_LEFT;
				case ">>": return BITSHIFT_RIGHT;
				case "AND": return AND;
				case "NAND": return NAND;
				case "XOR": return XOR;
				case "XNOR": return XNOR;
				case "OR": return OR;
				case "NOR": return NOR;
				case "<": return SMALLER;
				case ">": return LARGER;
				case "<=": return SMALLER_OR_EQUAL;
				case ">=": return LARGER_OR_EQUAL;
				case "==": return EQUAL;
				case "NOT": return NOT;
				case "AS": return AS;
				case "IS": return IS;
				case ",": return COMMA;
				case ":": return COLON;
				case "=": return ASSIGNMENT;
				default: return null;
			}
		}
		
		public SearchOrder searchOrder(){
			switch(this){
				//case COLON: return SearchOrder.RIGHT_TO_LEFT;
				default: return SearchOrder.LEFT_TO_RIGHT;
			}
		}
		
		public Position position(){
			switch(this){
				case INCREMENT: return Position.POSTFIX;
				case DECREMENT: return Position.POSTFIX;
				case NOT: return Position.PREFIX;
				case FACTORIAL: return Position.POSTFIX;
				case DEREFERENCE: return Position.PREFIX;
				case ADDRESS: return Position.PREFIX;
				default: return Position.INFIX;
			}
		}
	}
	
	public static enum SearchOrder {
		LEFT_TO_RIGHT,
		RIGHT_TO_LEFT
	}
	
	public static enum Position {
        PREFIX,
        INFIX,
        POSTFIX
    }
}
