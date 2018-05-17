//package cz.mg.compiler.tasks.c.resolver.other;
//
//import cz.mg.compiler.tasks.c.resolver.filters.Filter;
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.collections.ChainList;
//
//
//public class Context {
//    private final MgObject[] searchableObjects;
//
//    public Context(MgObject... searchableObjects) {
//        this.searchableObjects = searchableObjects;
//    }
//
//    public void findObjects(ChainList<MgObject> candidates, Filter filter) {
//        for(int i = 0; i < searchableObjects.length; i++){
//            if(searchableObjects[i] != null) findObjects(searchableObjects[i], candidates, filter);
//        }
//    }
//	
//	private void findObjects(MgObject parent, ChainList<MgObject> objects, Filter filter){
//		for(MgObject object : parent.getChildren()) if(filter.accept(object)) objects.addLast(object);
//	}
//}
