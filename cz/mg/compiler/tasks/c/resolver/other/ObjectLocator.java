//package cz.mg.compiler.tasks.c.resolver.other;
//
//import cz.mg.compiler.tasks.c.resolver.filters.Filter;
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.collections.ChainList;
//
//
//public class ObjectLocator {
//    private final Context context;
//    private final Filter filter;
//
//    public ObjectLocator(Context context, Filter filter) {
//        this.context = context;
//        this.filter = filter;
//    }
//    
//    public ChainList<MgObject> findObjects() {
//        return findObjects_s(context, filter);
//    }
//    
//    public static ChainList<MgObject> findObjects_s(Context context, Filter filter){
//        ChainList<MgObject> candidates = new ChainList<>();
//        context.findObjects(candidates, filter);
////        if(candidate instanceof Protected){
////            MgAccess access = ((Protected)candidate).getAccess();
////            if(!access.isAccessible(this)) throw filter.onInaccessible(candidate);
////        }
//        return candidates;
//    }
//}
