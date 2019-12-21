package cz.mg.compiler.tasks.builder.project;

import cz.mg.compiler.entities.logical.project.SourceFiles;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.Pattern;
import cz.mg.compiler.tasks.builder.utilities.Rule;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.tasks.builder.utilities.Filter.*;


public class BuildSourceFilesTask extends BlockBuildTask {
    private final Rules rules = new Rules(
            new Rule(new Pattern(_VALUE_), this::buildFilePath)
    );

    private SourceFiles sourceFiles;

    public BuildSourceFilesTask(Block block) {
        super(block);
    }

    public SourceFiles getSourceFiles() {
        return sourceFiles;
    }

    @Override
    public Rules getRules() {
        return rules;
    }

    @Override
    protected void build(Block block) {
        this.sourceFiles = new SourceFiles(block.getTrace());
    }

    private void buildFilePath(Block block){
        BuildFilePathTask task = new BuildFilePathTask(block);
        task.tryToRun();
        if(task.getFilePath() != null) sourceFiles.getFiles().addLast(task.getFilePath());
    }
}
