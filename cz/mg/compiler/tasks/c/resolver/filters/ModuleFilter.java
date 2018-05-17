//package cz.mg.compiler.tasks.c.resolver.filters;
//
//import cz.mg.compiler.entities.c.mg.MgModule;
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.lang.utilities.SubString;
//import cz.mg.collections.ChainList;
//import cz.mg.lang.utilities.error.CompileException;
//
//
//public class ModuleFilter implements Filter {
//    private final SubString name;
//
//    public ModuleFilter(SubString name) {
//        this.name = name;
//    }
//    
//    @Override
//    public boolean accept(MgObject object) {
//        if(!(object instanceof MgModule)) return false;
//        MgModule module = (MgModule)object;
//        if(!module.getName().equals(name)) return false;
//        return true;
//    }
//
//    @Override
//    public CompileException onNotFound() {
//        return new CompileException(name, "Could not find module ", name, ".");
//    }
//
//    @Override
//    public CompileException onMultipleFound(ChainList<MgObject> candidates) {
//        return new CompileException(name, "Ambiguous module ", name, ". Candidates are:\n\t", candidates, "\n\t");
//    }
//}
