package cz.mg.compiler.entities.logical.project;

import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.ToplevelEntity;


public class Project extends ToplevelEntity {
    @Child
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
