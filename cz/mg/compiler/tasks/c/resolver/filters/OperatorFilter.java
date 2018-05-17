//package cz.mg.compiler.tasks.c.resolver.filters;
//
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.compiler.entities.c.mg.MgOperator;
//import cz.mg.compiler.entities.c.mg.MgOperator.Type;
//import cz.mg.lang.utilities.SubString;
//import cz.mg.collections.ChainList;
//import cz.mg.lang.utilities.error.CompileException;
//
//
//public class OperatorFilter implements Filter {
//    private final Type type;
//    private final SubString name;
//
//    public OperatorFilter(SubString name, Type type) {
//        this.name = name;
//        this.type = type;
//    }
//    
//    @Override
//    public boolean accept(MgObject object) {
//        if(!(object instanceof MgOperator)) return false;
//        MgOperator operator = (MgOperator)object;
//        if(!operator.getType().equals(type)) return false;
//        return true;
//    }
//
//    @Override
//    public CompileException onNotFound() {
//        return new CompileException(name, "Could not find operator ", name, ".");
//    }
//
//    @Override
//    public CompileException onMultipleFound(ChainList<MgObject> candidates) {
//        return new CompileException(name, "Ambiguous operator ", name, ". Candidates are:\n\t", candidates, "\n\t");
//    }
//}
