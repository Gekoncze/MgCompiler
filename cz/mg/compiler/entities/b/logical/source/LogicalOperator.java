package cz.mg.compiler.entities.b.logical.source;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.tokens.OperatorToken;
import cz.mg.compiler.entities.b.logical.Logical;


public class LogicalOperator extends Logical<LogicalSourcePage, Logical> {
    private String name;
    private OperatorToken.Operator operator;
    
    public LogicalOperator(LogicalSourcePage parent, Location location) {
        super(parent, location);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OperatorToken.Operator getOperator() {
        return operator;
    }

    public void setOperator(OperatorToken.Operator operator) {
        this.operator = operator;
    }
}
