//package cz.mg.compiler.entities.c.mg.commands;
//
//import cz.mg.compiler.entities.c.mg.MgExpression;
//import cz.mg.compiler.entities.c.mg.MgFunction;
//import cz.mg.compiler.entities.c.mg.MgOperator;
//
//
//public class MgWhileCommand extends MgCommand {
//    private final MgExpression condition;
//
//    public MgWhileCommand(MgFunction parent, MgExpression condition) {
//        super(parent);
//        this.condition = condition;
//    }
//    
//    public MgWhileCommand(MgOperator parent, MgExpression condition) {
//        super(parent);
//        this.condition = condition;
//    }
//    
//    public MgWhileCommand(MgCommand parent, MgExpression condition) {
//        super(parent);
//        this.condition = condition;
//    }
//
//    public MgExpression getCondition() {
//        return condition;
//    }
//}
