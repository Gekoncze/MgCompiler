//package cz.mg.compiler.tasks.c.resolver;
//
//
//public class ModuleResolver {
//        
////    public LogicalModule getModule(Iterator<String> modulePath, boolean create) {
////        String name = modulePath.next();
////		
////		ChainList<MgObject> modules = new ChainList<>();
////		findObjects(modules, new ModuleFilter(name));
////		
////		if(modules.size() > 1) throw new CompileException(name, "Module ", name, " is ambiguous.");
////		MgModule module = (MgModule) modules.getFirst();
////		        
////        if(module == null){
////            if(create){
////                module = new MgModule(this);
////				module.setName(name);
////                add(module);
////            } else {
////                throw new CompileException(name, "Could not find module ", name, " in ", this);
////            }
////        }
////        
////        if(modulePath.hasNext()){
////            return module.getModule(modulePath, create);
////        } else {
////            return module;
////        }
////    }  
//}
