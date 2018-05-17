//package cz.mg.compiler.entities.c.mg.calls;
//
//import cz.mg.compiler.entities.c.mg.MgType;
//import cz.mg.compiler.entities.c.mg.MgVariable;
//import cz.mg.compiler.entities.c.mg.MgVariableGroup;
//import cz.mg.compiler.entities.c.mg.MgObject;
//
//
//public class MgLiteralCall extends MgCall {
//	private final MgType type;
//	private final String value;
//	
//	public MgLiteralCall(MgObject parent, MgType type, String value) {
//		super(parent);
//		this.type = type;
//		this.value = value;
//	}
//
//	public MgType getType() {
//		return type;
//	}
//
//	public String getValue() {
//		return value;
//	}
//
//	@Override
//	public MgVariableGroup getOutput() {
//		return new MgVariableGroup(new MgVariable(null, type, null));
//	}
//}
