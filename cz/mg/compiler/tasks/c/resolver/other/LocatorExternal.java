//package cz.mg.compiler.tasks.c.resolver.other;
//
//import cz.mg.compiler.entities.b.logical.External;
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.lang.utilities.error.CompileException;
//
//
//public class LocatorExternal<T extends MgObject> implements External<T> {
//    private final Locator locator;
//    private T object = null;
//    private boolean locked = false;
//    
//    public LocatorExternal(Locator locator){
//        this.locator = locator;
//        this.object = null;
//    }
//    
//    public LocatorExternal(T object){
//        if(object == null) throw new RuntimeException();
//        this.locator = null;
//        this.object = object;
//    }
//
//    public T getObject() {
//        if(object == null) object = findObject();
//        return object;
//    }
//	
//	private T findObject(){
//		if(locked) throw new CompileException(null, "Cyclic locating detected.");
//		try {
//			locked = true;
//			return (T) locator.findObject();
//		} finally {
//			locked = false;
//		}
//	}
//
//	@Override
//	public void reset() {
//		object = null;
//	}
//}
