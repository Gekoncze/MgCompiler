package cz.mg.compiler.entities.a.segments.tokens.plain;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.a.segments.tokens.OperatorToken;


public class PlainOperatorToken extends Word implements OperatorToken {
	private final Operator operator;

	public PlainOperatorToken(Line parent, Location location, String text, Operator operator) {
		super(parent, location, text);
		this.operator = operator;
	}

	@Override
	public Operator getOperator() {
		return operator;
	}
}
