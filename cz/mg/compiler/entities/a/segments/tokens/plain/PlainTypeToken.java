package cz.mg.compiler.entities.a.segments.tokens.plain;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.a.segments.tokens.TypeToken;


public class PlainTypeToken extends Word implements TypeToken {
	private final int pointerCount;
	
    public PlainTypeToken(Line parent, Location location, String text, int pointerCount) {
        super(parent, location, text);
		this.pointerCount = pointerCount;
    }

	@Override
	public int getPointerCount() {
		return pointerCount;
	}
}
