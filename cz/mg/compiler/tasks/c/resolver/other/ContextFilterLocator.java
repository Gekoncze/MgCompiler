//package cz.mg.compiler.tasks.c.resolver.other;
//
//import cz.mg.compiler.tasks.c.resolver.other.Locator;
//import cz.mg.compiler.tasks.c.resolver.filters.Filter;
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.collections.ChainList;
//
//
//public class ContextFilterLocator implements Locator {
//	private final Context context;
//	private final Filter filter;
//
//	public ContextFilterLocator(Context context, Filter filter) {
//		this.context = context;
//		this.filter = filter;
//	}
//	
//	@Override
//	public MgObject findObject() {
//		ChainList<MgObject> candidates = new ChainList<>();
//        context.findObjects(candidates, filter);
//        if(candidates.size() <= 0) throw filter.onNotFound();
//        if(candidates.size() > 1) throw filter.onMultipleFound(candidates);
//        MgObject candidate = candidates.getFirst();
//        return candidate;
//	}
//}
