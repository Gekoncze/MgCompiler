package cz.mg.compiler.tasks.b.composer.source;

import cz.mg.compiler.entities.a.segments.Line;


public interface Rule {
    public boolean applicable(Line line);
}
