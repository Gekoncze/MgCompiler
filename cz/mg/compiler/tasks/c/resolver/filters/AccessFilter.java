//package cz.mg.compiler.tasks.c.resolver.filters;
//
//import cz.mg.compiler.entities.c.mg.MgAccess;
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.lang.utilities.SubString;
//import cz.mg.collections.ChainList;
//import cz.mg.lang.utilities.error.CompileException;
//
//
//public class AccessFilter implements Filter {
//    private final SubString name;
//
//    public AccessFilter(SubString name) {
//        this.name = name;
//    }
//    
//    @Override
//    public boolean accept(MgObject object) {
//        if(!(object instanceof MgAccess)) return false;
//        MgAccess access = (MgAccess)object;
//        if(!access.getName().equals(name)) return false;
//        return true;
//    }
//
//    @Override
//    public CompileException onNotFound() {
//        return new CompileException(name, "Could not find access ", name, ".");
//    }
//
//    @Override
//    public CompileException onMultipleFound(ChainList<MgObject> candidates) {
//        return new CompileException(name, "Ambiguous access ", name, ". Candidates are:\n\t", candidates, "\n\t");
//    }
//}
