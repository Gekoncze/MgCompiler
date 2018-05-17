package cz.mg.compiler.tasks.d.translator.c;

//package cz.mg.translator.c;
//
//import cz.mg.lang.utilities.error.CompileException;
//import cz.mg.lang.MgModule;
//import cz.mg.lang.MgType;
//import cz.mg.lang.MgTypename;
//import cz.mg.lang.buildintypes.MgBuildinType;
//import java.util.LinkedList;
//
//
//public class CTypeDependencies {
//    private final LinkedList<MgType> dependencies = new LinkedList<>();
//    private final LinkedList<MgType> pointerDependencies = new LinkedList<>();
//    private final LinkedList<MgModule> buildinDependencies = new LinkedList<>();
//    
//    public void add(MgType externalType) throws CompileException {
//        MgType type = externalType;
//        MgTypename typename = type.getTypename();
//        if(typename instanceof MgBuildinType){
////            MgModule etModule = typename.;
////            for(MgModule dependency : buildinDependencies){
////                String etModuleName = etModule.getFullName().toString(".");
////                String dModuleName = dependency.getFullName().toString(".");
////                if(etModuleName.equals(dModuleName)) return;
////            }
////            buildinDependencies.add(etModule);
////			buildinDependencies.add()
//        } else if(type.getPointerCount() <= 0){
//            for(MgType dependency : dependencies){
//                String etFullName = typename.getFullName().toString(".");
//                String dFullName = dependency.getTypename().getFullName().toString(".");
//                if(etFullName.equals(dFullName)) return;
//            }
//            dependencies.add(externalType);
//        } else {
//            for(MgType dependency : pointerDependencies){
//                String etFullName = typename.getFullName().toString(".");
//                String dFullName = dependency.getTypename().getFullName().toString(".");
//                if(etFullName.equals(dFullName)) return;
//            }
//            pointerDependencies.add(externalType);
//        }
//    }
//
//    public Iterable<MgType> getDependencies() {
//        return dependencies;
//    }
//    
//    public Iterable<MgType> getPointerDependencies() {
//        return pointerDependencies;
//    }
//
//    public LinkedList<MgModule> getBuildinDependencies() {
//        return buildinDependencies;
//    }
//}
