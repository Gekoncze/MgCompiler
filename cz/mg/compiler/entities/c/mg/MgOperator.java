//package cz.mg.compiler.entities.c.mg;
//
//import cz.mg.compiler.tasks.CompileException;
//
//
//public class MgOperator extends MgObject {
//    private final Type type;
//	private final MgVariable left;
//	private final MgVariable right;
//	private final MgVariable output;
//    
//    public MgOperator(MgModule parent, MgAccess access, String name, Type type, MgVariable left, MgVariable right, MgVariable output) {
//        super(parent, access, name);
//        this.type = type;
//		this.left = left;
//        this.right = right;
//        this.output = output;
//    }
//
//    public Type getType() {
//        return type;
//    }
//
//    public MgVariable getLeft() {
//        return left;
//    }
//
//    public MgVariable getRight() {
//        return right;
//    }
//
//    public MgVariable getOutput() {
//        return output;
//    }
//    
//    public static enum Type {
//		INCREMENT,
//		DECREMENT,
//        ADDRESS,
//        DEREFERENCE,
//        FACTORIAL,
//        POWER,
//        MULTIPLY,
//        INTEGER_DIVIDE,
//        MODULO,
//        REAL_DIVIDE,
//        PLUS,
//        MINUS,
//        BITSHIFT_LEFT,
//        BITSHIFT_RIGHT,
//        AND,
//        NAND,
//        XOR,
//        XNOR,
//        OR,
//        NOR,
//        SMALLER,
//        LARGER,
//        SMALLER_OR_EQUAL,
//        LARGER_OR_EQUAL,
//		EQUAL,
//		NOT,
//        AS,
//		IS;
//        
//        public static Type fromString(String value){
//            switch(value){
//                case "++": return INCREMENT;
//                case "--": return DECREMENT;
//                case "&": return ADDRESS;
//                case "@": return DEREFERENCE;
//                case "!": return FACTORIAL;
//                case "^": return POWER;
//                case "*": return MULTIPLY;
//                case "\\": return INTEGER_DIVIDE;
//                case "%": return MODULO;
//                case "/": return REAL_DIVIDE;
//                case "+": return PLUS;
//                case "-": return MINUS;
//                case "<<": return BITSHIFT_LEFT;
//                case ">>": return BITSHIFT_RIGHT;
//                case "AND": return AND;
//                case "NAND": return NAND;
//                case "XOR": return XOR;
//                case "OR": return OR;
//                case "NOR": return NOR;
//                case "<": return SMALLER;
//                case ">": return LARGER;
//                case "<=": return SMALLER_OR_EQUAL;
//                case ">=": return LARGER_OR_EQUAL;
//                case "==": return EQUAL;
//                case "NOT": return NOT;
//                case "AS": return AS;
//                case "IS": return IS;
//                default: throw new CompileException(null, "Unknown operator ", value, ".");
//            }
//        }
//    }
//}
