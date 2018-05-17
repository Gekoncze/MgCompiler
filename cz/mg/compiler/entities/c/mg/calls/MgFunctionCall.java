//package cz.mg.compiler.entities.c.mg.calls;
//
//import cz.mg.compiler.entities.c.mg.MgFunction;
//import cz.mg.compiler.entities.c.mg.MgVariableGroup;
//import cz.mg.collections.ChainList;
//import cz.mg.compiler.entities.c.mg.MgObject;
//
//
//public class MgFunctionCall extends MgCall {
//	private final MgFunction function;
//	private final ChainList<MgCall> argumentCalls;
//
//	public MgFunctionCall(MgObject parent, MgFunction function, ChainList<MgCall> argumentCalls) {
//		super(parent);
//		this.function = function;
//		this.argumentCalls = argumentCalls;
//	}
//
//	public MgFunction getFunction() {
//		return function;
//	}
//
//	public ChainList<MgCall> getArgumentCalls() {
//		return argumentCalls;
//	}
//
//	@Override
//	public MgVariableGroup getOutput() {
//		return function.getOutput();
//	}
//}
