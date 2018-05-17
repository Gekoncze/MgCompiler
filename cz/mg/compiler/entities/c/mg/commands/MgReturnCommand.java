//package cz.mg.compiler.entities.c.mg.commands;
//
//import cz.mg.compiler.entities.c.mg.MgExpression;
//import cz.mg.compiler.entities.c.mg.MgFunction;
//import cz.mg.compiler.entities.c.mg.MgOperator;
//
//
//public class MgReturnCommand extends MgCommand {
//    private final MgExpression expression;
//    
//    public MgReturnCommand(MgFunction parent, MgExpression expression) {
//        super(parent);
//		this.expression = expression;
//    }
//    
//    public MgReturnCommand(MgOperator parent, MgExpression expression) {
//        super(parent);
//		this.expression = expression;
//    }
//    
//    public MgReturnCommand(MgCommand parent, MgExpression expression) {
//        super(parent);
//		this.expression = expression;
//    }
//
//    public MgExpression getExpression() {
//        return expression;
//    }
//}
