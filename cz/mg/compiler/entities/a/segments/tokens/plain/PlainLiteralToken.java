package cz.mg.compiler.entities.a.segments.tokens.plain;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Word;
import cz.mg.compiler.entities.a.segments.tokens.LiteralToken;


public class PlainLiteralToken extends Word implements LiteralToken {
    public PlainLiteralToken(Line parent, Location location, String text) {
        super(parent, location, text);
    }
}
