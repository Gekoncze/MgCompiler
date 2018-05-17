package cz.mg.compiler.entities.a.segments.tokens.plain;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;


public class PlainKeywordToken extends Word implements KeywordToken {
	private final Keyword keyword;

	public PlainKeywordToken(Line parent, Location location, String text, Keyword keyword) {
		super(parent, location, text);
		this.keyword = keyword;
	}

	@Override
	public Keyword getKeyword() {
		return keyword;
	}
}
