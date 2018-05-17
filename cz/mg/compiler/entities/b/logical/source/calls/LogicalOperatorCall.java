package cz.mg.compiler.entities.b.logical.source.calls;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.tokens.OperatorToken;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalOperatorCall extends LogicalCall {
	private final OperatorToken.Operator operator;
	private final String operatorString;

    public LogicalOperatorCall(Location location, OperatorToken.Operator operator, String operatorString) {
        super(location);
        this.operator = operator;
        this.operatorString = operatorString;
    }
    
    public LogicalOperatorCall(Logical parent, Location location, OperatorToken.Operator operator, String operatorString) {
        super(parent, location);
        this.operator = operator;
        this.operatorString = operatorString;
    }

    public OperatorToken.Operator getOperator() {
        return operator;
    }

    public String getOperatorString() {
        return operatorString;
    }
}
