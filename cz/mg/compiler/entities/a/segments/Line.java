package cz.mg.compiler.entities.a.segments;

import cz.mg.compiler.Location;


public class Line extends Segment<Segment, Segment> {
    public Line(Segment parent, Location location) {
        super(parent, location);
    }
}
