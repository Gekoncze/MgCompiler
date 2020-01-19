package cz.mg.compiler.entities.logical;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.ToplevelEntity;
import cz.mg.compiler.entities.logical.mg.LogicalMg;
import cz.mg.compiler.entities.logical.project.Project;


public class Logic extends ToplevelEntity {
    @Part
    private final Project project = new Project();

    @Part
    private final LogicalMg logicalMg = new LogicalMg();

    public Logic() {
    }

    public Project getProject() {
        return project;
    }

    public LogicalMg getLogicalMg() {
        return logicalMg;
    }
}
