//package cz.mg.compiler.entities.c.mg.commands;
//
//import cz.mg.compiler.entities.c.mg.MgExpression;
//import cz.mg.compiler.entities.c.mg.MgFunction;
//import cz.mg.compiler.entities.c.mg.MgOperator;
//
//
//public class MgIfCommand extends MgCommand {
//    private final MgExpression condition;
//
//    public MgIfCommand(MgFunction parent, MgExpression condition) {
//        super(parent);
//		this.condition = condition;
//    }
//    
//    public MgIfCommand(MgOperator parent, MgExpression condition) {
//        super(parent);
//		this.condition = condition;
//    }
//    public MgIfCommand(MgCommand parent, MgExpression condition) {
//        super(parent);
//		this.condition = condition;
//    }
//    
//
//    public MgExpression getCondition() {
//        return condition;
//    }
//}
