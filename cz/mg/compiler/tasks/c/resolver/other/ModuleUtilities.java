//package cz.mg.compiler.tasks.c.resolver.other;
//
//import cz.mg.collections.ChainList;
//import cz.mg.compiler.entities.b.logical.source.LogicalModule;
//
//
//public class ModuleUtilities {
//    public LogicalModule getOrCreateModule(ChainList<String> modulePath) {
//        return getModule(modulePath.iterator(), true);
//    }
//
//    public LogicalModule getModule(ChainList<String> modulePath) {
//        return getModule(modulePath.iterator(), false);
//    }
//    
//    public LogicalModule createBuildinModule(String modulePath) {
//        ChainList<String> path = new ChainList<>();
//        String[] names = modulePath.split("\\.");
//        for(int i = 0; i < names.length; i++){
//            path.addLast(new String(names[i]));
//        }
//        return getOrCreateModule(path);
//    }
//    
//    public LogicalModule getBuildinModule(String modulePath) {
//        ChainList<String> path = new ChainList<>();
//        String[] names = modulePath.split("\\.");
//        for(int i = 0; i < names.length; i++){
//            path.addLast(new String(names[i]));
//        }
//        return getModule(path);
//    }
//}
