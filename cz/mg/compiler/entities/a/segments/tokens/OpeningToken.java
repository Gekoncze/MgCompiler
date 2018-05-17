package cz.mg.compiler.entities.a.segments.tokens;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Word;


public class OpeningToken extends Word implements Token {
	public OpeningToken(Line parent, Location location, String text) {
		super(parent, location, text);
	}
}
