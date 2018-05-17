//package cz.mg.compiler.tasks.c.resolver.filters;
//
//import cz.mg.compiler.entities.c.mg.MgFunction;
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.lang.utilities.SubString;
//import cz.mg.collections.ChainList;
//import cz.mg.lang.utilities.error.CompileException;
//
//
//public class FunctionFilter implements Filter {
//    private final SubString name;
//
//    public FunctionFilter(SubString name) {
//        this.name = name;
//    }
//    
//    @Override
//    public boolean accept(MgObject object) {
//        if(!(object instanceof MgFunction)) return false;
//        MgFunction function = (MgFunction)object;
//        if(!function.getName().equals(name)) return false;
//        return true;
//    }
//
//    @Override
//    public CompileException onNotFound() {
//        return new CompileException(name, "Could not find function ", name, ".");
//    }
//
//    @Override
//    public CompileException onMultipleFound(ChainList<MgObject> candidates) {
//        return new CompileException(name, "Ambiguous function ", name, ". Candidates are:\n\t", candidates, "\n\t");
//    }
//}
