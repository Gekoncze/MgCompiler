//package cz.mg.compiler.tasks.c.resolver.filters;
//
//import cz.mg.compiler.entities.c.mg.MgObject;
//import cz.mg.collections.ChainList;
//import cz.mg.lang.utilities.error.CompileException;
//
//
//public class NoFilter implements Filter {
//	@Override
//	public boolean accept(MgObject object) {
//		return true;
//	}
//
//	@Override
//	public CompileException onNotFound() {
//		throw new RuntimeException();
//	}
//
//	@Override
//	public CompileException onMultipleFound(ChainList<MgObject> candidates) {
//		throw new RuntimeException();
//	}
//}
