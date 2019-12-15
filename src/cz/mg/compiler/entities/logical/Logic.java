package cz.mg.compiler.entities.logical;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.ToplevelEntity;
import cz.mg.compiler.entities.logical.language.Language;
import cz.mg.compiler.entities.logical.project.Project;


public class Logic extends ToplevelEntity {
    @Part
    private final Project project = new Project();

    @Part
    private final Language language = new Language();

    public Logic() {
    }

    public Project getProject() {
        return project;
    }

    public Language getLanguage() {
        return language;
    }
}
