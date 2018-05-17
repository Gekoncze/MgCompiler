package cz.mg.compiler.entities.a.segments.tokens.combined;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import cz.mg.compiler.entities.a.segments.tokens.OperatorToken;


public class CombinedKeywordOperatorToken extends Word implements KeywordToken, OperatorToken {
	private final Keyword keyword;
	private final Operator operator;

	public CombinedKeywordOperatorToken(Line parent, Location location, String text, Keyword keyword, Operator operator) {
		super(parent, location, text);
		this.keyword = keyword;
		this.operator = operator;
	}

	@Override
	public Keyword getKeyword() {
		return keyword;
	}

	@Override
	public Operator getOperator() {
		return operator;
	}
}
