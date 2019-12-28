package cz.mg.compiler.tasks.builder.project;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.project.FilePath;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Value;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.Rules;


public class BuildFilePathTask extends BlockBuildTask {
    @Link
    private FilePath filePath = null;

    public BuildFilePathTask(Block block) {
        super(block);
    }

    public FilePath getFilePath() {
        return filePath;
    }

    @Override
    protected Rules getRules() {
        return null;
    }

    @Override
    protected void build(Block block) {
        Value value = (Value)block.getParts().getFirst();
        filePath = new FilePath(value.getValue());
    }
}
