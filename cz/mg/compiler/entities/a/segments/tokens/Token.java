package cz.mg.compiler.entities.a.segments.tokens;

import cz.mg.compiler.Location;


public interface Token {
    public String getText();
    public Location getLocation();
}
