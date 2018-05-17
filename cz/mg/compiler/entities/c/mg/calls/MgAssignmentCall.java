//package cz.mg.compiler.entities.c.mg.calls;
//
//import cz.mg.compiler.entities.c.mg.MgVariableGroup;
//import cz.mg.collections.ChainList;
//import cz.mg.compiler.entities.c.mg.MgObject;
//
//
//public class MgAssignmentCall extends MgCall {
//	private final ChainList<MgCall> leftArguments;
//	private final ChainList<MgCall> rightArguments;
//	private final MgVariableGroup output;
//
//	public MgAssignmentCall(MgObject parent, ChainList<MgCall> leftArguments, ChainList<MgCall> rightArguments) {
//		super(parent);
//		this.leftArguments = leftArguments;
//		this.rightArguments = rightArguments;
//		this.output = new MgVariableGroup(this, false, false);
//	}
//
//	@Override
//	public MgVariableGroup getOutput() {
//		return output;
//	}
//}
