package cz.mg.compiler.tasks.builder.project;

import cz.mg.compiler.entities.logical.project.Project;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Container;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.*;
import static cz.mg.compiler.tasks.builder.utilities.Filter.*;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.*;


public class BuildProjectTask extends BlockBuildTask {
    private final Rules rules = new Rules(
            new Rule(new Pattern(SOURCE, FILES), this::buildSourceFiles)
    );

    private final Project project;

    public BuildProjectTask(Project project, Container projectPage) {
        super(projectPage);
        this.project = project;
    }

    @Override
    public Rules getRules() {
        return rules;
    }

    @Override
    protected void build(Block block) {
    }

    private void buildSourceFiles(Block block) {
        BuildSourceFilesTask task = new BuildSourceFilesTask(block);
        task.tryToRun();
        store(project, "sourceFiles", task.getSourceFiles());
    }
}
