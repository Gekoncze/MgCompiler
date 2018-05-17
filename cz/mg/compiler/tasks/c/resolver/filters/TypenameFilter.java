//package cz.mg.compiler.tasks.c.resolver.filters;
//
//import cz.mg.compiler.entities.c.mg.MgTypename;
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.lang.utilities.SubString;
//import cz.mg.collections.ChainList;
//import cz.mg.lang.utilities.error.CompileException;
//
//
//public class TypenameFilter implements Filter {
//    private final SubString name;
//
//    public TypenameFilter(SubString name) {
//        this.name = name;
//    }
//    
//    @Override
//    public boolean accept(MgObject object) {
//        if(object instanceof MgTypename){
//            if(((MgTypename)object).getName().equals(name)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public CompileException onNotFound() {
//        return new CompileException(name, "Could not find typename ", name, ".");
//    }
//
//    @Override
//    public CompileException onMultipleFound(ChainList<MgObject> candidates) {
//        return new CompileException(name, "Ambiguous typename ", name, ". Candidates are:\n\t", candidates, "\n\t");
//    }
//}
