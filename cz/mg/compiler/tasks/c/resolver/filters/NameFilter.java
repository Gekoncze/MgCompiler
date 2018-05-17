//package cz.mg.compiler.tasks.c.resolver.filters;
//
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.lang.utilities.SubString;
//import cz.mg.collections.ChainList;
//import cz.mg.lang.utilities.error.CompileException;
//
//
//public class NameFilter implements Filter {
//    private final SubString name;
//
//    public NameFilter(SubString name) {
//        this.name = name;
//    }
//    
//    @Override
//    public boolean accept(MgObject object) {
//        if(!object.getName().equals(name)) return false;
//        return true;
//    }
//
//    @Override
//    public CompileException onNotFound() {
//        return new CompileException(name, "Could not find ", name, ".");
//    }
//
//    @Override
//    public CompileException onMultipleFound(ChainList<MgObject> candidates) {
//        return new CompileException(name, "Ambiguous name ", name, ". Candidates are:\n\t", candidates, "\n\t");
//    }
//}
