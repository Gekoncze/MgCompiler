//package cz.mg.compiler.entities.c.mg.calls;
//
//import cz.mg.compiler.entities.c.mg.MgVariable;
//import cz.mg.compiler.entities.c.mg.MgVariableGroup;
//import cz.mg.compiler.entities.c.mg.MgObject;
//
//
//public class MgVariableCall extends MgCall {
//	private final MgVariable variable;
//
//	public MgVariableCall(MgObject parent, MgVariable variable) {
//		super(parent);
//		this.variable = variable;
//	}
//
//	public MgVariable getVariable() {
//		return variable;
//	}
//
//	@Override
//	public MgVariableGroup getOutput() {
//		return new MgVariableGroup(variable);
//	}
//}
