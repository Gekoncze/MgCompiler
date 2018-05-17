package cz.mg.compiler.entities.a.segments.tokens.combined;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.a.segments.tokens.KeywordToken;
import cz.mg.compiler.entities.a.segments.tokens.TypeToken;


public class CombinedKeywordTypeToken extends Word implements KeywordToken, TypeToken {
	private final Keyword keyword;
	private final int pointerCount;

	public CombinedKeywordTypeToken(Line parent, Location location, String text, Keyword keyword, int pointerCount) {
		super(parent, location, text);
		this.keyword = keyword;
		this.pointerCount = pointerCount;
	}
	
	@Override
	public Keyword getKeyword() {
		return keyword;
	}

	@Override
	public int getPointerCount() {
		return pointerCount;
	}
}
