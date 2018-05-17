package cz.mg.compiler.entities.a.segments.tokens;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.tokens.plain.PlainTypeToken;


public interface TypeToken extends Token {
	public static TypeToken create(Line parent, Location location, String text, int pointerCount){
		return new PlainTypeToken(parent, location, text, pointerCount);
	}
	
	public int getPointerCount();
}
