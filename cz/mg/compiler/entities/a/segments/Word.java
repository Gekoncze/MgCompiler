package cz.mg.compiler.entities.a.segments;

import cz.mg.compiler.Location;


public class Word extends Segment<Line, Segment> {
    private final String text;

    public Word(Line parent, Location location, String text) {
        super(parent, location);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
