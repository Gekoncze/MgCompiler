//package cz.mg.compiler.entities.c.mg.calls;
//
//import cz.mg.compiler.entities.c.mg.MgOperator;
//import cz.mg.compiler.entities.c.mg.MgVariableGroup;
//import cz.mg.compiler.entities.c.mg.MgObject;
//
//
//public class MgOperatorCall extends MgCall {
//	private final MgOperator operator;
//	private final MgCall left;
//	private final MgCall right;
//
//	public MgOperatorCall(MgObject parent, MgOperator operator, MgCall left, MgCall right) {
//		super(parent);
//		if(operator == null) throw new NullPointerException();
//		if(left == null) throw new NullPointerException();
//		if(right == null) throw new NullPointerException();
//		if(left.getOutput().size() != 1) throw new RuntimeException();
//		if(right.getOutput().size() != 1) throw new RuntimeException();
//		this.operator = operator;
//		this.left = left;
//		this.right = right;
//	}
//
//	public MgOperator getOperator() {
//		return operator;
//	}
//
//	public MgCall getLeft() {
//		return left;
//	}
//
//	public MgCall getRight() {
//		return right;
//	}
//
//	@Override
//	public MgVariableGroup getOutput() {
//		return operator.getOutput();
//	}
//}
