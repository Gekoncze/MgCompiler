//package cz.mg.compiler.entities.c.mg.commands;
//
//import cz.mg.compiler.entities.c.mg.MgExpression;
//import cz.mg.compiler.entities.c.mg.MgFunction;
//import cz.mg.compiler.entities.c.mg.MgOperator;
//
//
//public class MgElseIfCommand extends MgCommand {
//    private final MgExpression condition;
//
//    public MgElseIfCommand(MgFunction parent, MgExpression condition) {
//        super(parent);
//		this.condition = condition;
//    }
//    
//    public MgElseIfCommand(MgOperator parent, MgExpression condition) {
//        super(parent);
//		this.condition = condition;
//    }
//    
//    public MgElseIfCommand(MgCommand parent, MgExpression condition) {
//        super(parent);
//		this.condition = condition;
//    }
//    
//    public MgExpression getCondition() {
//        return condition;
//    }
//}
