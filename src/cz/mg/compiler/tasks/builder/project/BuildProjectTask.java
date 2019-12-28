package cz.mg.compiler.tasks.builder.project;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.annotations.Part;
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

    @Link
    private final Project project;

    @Part
    private final ChainList<BuildSourceFilesTask> buildSourceFilesTasks = new ChainList<>();

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
        buildSourceFilesTasks.addLast(task);
        task.tryToRun();
        store(project, "sourceFiles", task.getSourceFiles());
    }
}
