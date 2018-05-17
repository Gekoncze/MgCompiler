//package cz.mg.compiler.entities.c.mg.calls;
//
//import cz.mg.compiler.entities.c.mg.MgVariableGroup;
//import cz.mg.compiler.entities.c.mg.MgObject;
//
//
//public class MgMemberAccessCall extends MgCall {
//	private final MgCall outer;
//	private final MgCall member;
//
//	public MgMemberAccessCall(MgObject parent, MgCall left, MgCall right) {
//		super(parent);
//		this.outer = left;
//		this.member = right;
//	}
//
//	public MgCall getOuter() {
//		return outer;
//	}
//
//	public MgCall getMember() {
//		return member;
//	}
//	
//	@Override
//	public MgVariableGroup getOutput() {
//		return member.getOutput();
//	}
//}
