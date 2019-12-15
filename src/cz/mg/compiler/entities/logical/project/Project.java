package cz.mg.compiler.entities.logical.project;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.ToplevelEntity;


public class Project extends ToplevelEntity {
    @Part
    private SourceFiles sourceFiles = null;

    public Project() {
    }

    public SourceFiles getSourceFiles() {
        return sourceFiles;
    }

    public void setSourceFiles(SourceFiles sourceFiles) {
        this.sourceFiles = sourceFiles;
    }
}
