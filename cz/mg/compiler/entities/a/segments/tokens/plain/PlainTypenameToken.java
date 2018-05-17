package cz.mg.compiler.entities.a.segments.tokens.plain;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.a.segments.tokens.TypenameToken;


public class PlainTypenameToken extends Word implements TypenameToken {
    public PlainTypenameToken(Line parent, Location location, String text) {
        super(parent, location, text);
    }

	@Override
	public int getPointerCount() {
		return 0;
	}
}
