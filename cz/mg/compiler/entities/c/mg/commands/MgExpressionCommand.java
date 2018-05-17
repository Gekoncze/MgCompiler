//package cz.mg.compiler.entities.c.mg.commands;
//
//import cz.mg.compiler.entities.c.mg.MgExpression;
//import cz.mg.compiler.entities.c.mg.MgFunction;
//import cz.mg.compiler.entities.c.mg.MgOperator;
//
//
//public class MgExpressionCommand extends MgCommand {
//    private final MgExpression expression;
//    
//    public MgExpressionCommand(MgFunction parent, MgExpression expression) {
//        super(parent);
//        this.expression = expression;
//    }
//    
//    public MgExpressionCommand(MgOperator parent, MgExpression expression) {
//        super(parent);
//        this.expression = expression;
//    }
//    
//    public MgExpressionCommand(MgCommand parent, MgExpression expression) {
//        super(parent);
//        this.expression = expression;
//    }
//
//    public MgExpression getExpression() {
//        return expression;
//    }
//}
