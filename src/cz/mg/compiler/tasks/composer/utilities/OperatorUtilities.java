package cz.mg.compiler.tasks.composer.utilities;


import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;


public class OperatorUtilities {
    private static final String[][] OPERATORS = new String[][]{
            new String[]{ "_!", "+_", "-_" },
            new String[]{ "_^_" },
            new String[]{ "_*_", "_/_", "_\\_", "_%_" },
            new String[]{ "_+_", "_-_" },
            new String[]{ "_AS_" },
            new String[]{ "_=_", "_<_", "_>_", "_<=_", "_>=_", "_?_" },
            new String[]{ "_AND_", "_NAND_", "_OR_", "_NOR_", "_XOR_", "_NXOR_", "NOT_" },
            new String[]{ "_&=_", "_$=_", "_*=_", "_/=_", "_\\=_", "_%=_", "_+=_", "_-=_", "_<&>_", "_<$>_" }
    };

    public static final Op[][] OPS;
    public static final ChainList<Op> OPS_1 = new CachedChainList<>();
    public static final ChainList<Op> OPS_2 = new CachedChainList<>();
    public static final ChainList<Op> OPS_3 = new CachedChainList<>();
    public static final ChainList<Op> OPS_K = new CachedChainList<>();

    static {
        OPS = new Op[OPERATORS.length][];
        for(int i = 0; i < OPERATORS.length; i++){
            OPS[i] = new Op[OPERATORS[i].length];
            for(int j = 0; j < OPERATORS[i].length; j++){
                OPS[i][j] = new Op(OPERATORS[i][j]);
            }
        }

        for(int i = 0; i < OPS.length; i++){
            for(int j = 0; j < OPS[i].length; j++){
                if(!isKeyword(OPS[i][j].operator)){
                    if(OPS[i][j].operator.length() == 1){
                        OPS_1.addLast(OPS[i][j]);
                    }
                }
            }
        }

        for(int i = 0; i < OPS.length; i++){
            for(int j = 0; j < OPS[i].length; j++){
                if(!isKeyword(OPS[i][j].operator)){
                    if(OPS[i][j].operator.length() == 2){
                        OPS_2.addLast(OPS[i][j]);
                    }
                }
            }
        }

        for(int i = 0; i < OPS.length; i++){
            for(int j = 0; j < OPS[i].length; j++){
                if(!isKeyword(OPS[i][j].operator)){
                    if(OPS[i][j].operator.length() == 3){
                        OPS_3.addLast(OPS[i][j]);
                    }
                }
            }
        }

        for(int i = 0; i < OPS.length; i++){
            for(int j = 0; j < OPS[i].length; j++){
                if(isKeyword(OPS[i][j].operator)){
                    OPS_K.addLast(OPS[i][j]);
                }
            }
        }
    }

    private static boolean isKeyword(String operator){
        for(int i = 0; i < operator.length(); i++) if(!Character.isUpperCase(operator.charAt(i))) return false;
        return true;
    }

    public enum Position {
        LEFT,
        MIDDLE,
        RIGHT
    }

    public static class Op {
        public final String operator;
        public final Position position;

        public Op(String operator) {
            boolean left = operator.startsWith("_");
            boolean right = operator.endsWith("_");
            int lx = left ? 1 : 0;
            int rx = right ? operator.length() - 1 : operator.length();
            this.operator = operator.substring(lx, rx);
            this.position = getPosition(left, right);
        }

        private static Position getPosition(boolean left, boolean right){
            if(left && right) return Position.MIDDLE;
            if(left) return Position.RIGHT;
            if(right) return Position.LEFT;
            throw new RuntimeException();
        }
    }
}
