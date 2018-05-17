package cz.mg.compiler.entities.a.segments.tokens.combined;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import cz.mg.compiler.entities.a.segments.tokens.LiteralToken;


public class CombinedKeywordLiteralToken extends Word implements KeywordToken, LiteralToken {
	private final Keyword keyword;

	public CombinedKeywordLiteralToken(Line parent, Location location, String text, Keyword keyword) {
		super(parent, location, text);
		this.keyword = keyword;
	}

	@Override
	public Keyword getKeyword() {
		return keyword;
	}
}
