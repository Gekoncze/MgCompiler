package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.entities.logical.language.Language;
import cz.mg.compiler.entities.logical.language.Location;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.chains.Path;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.Rules;

import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.toPath;


public class BuildLocationTask extends BlockBuildTask {
    private final Language language;
    private Location location = null;

    public BuildLocationTask(Task parentTask, Block block, Language language) {
        super(parentTask, block, null);
        this.language = language;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    protected Rules getRules() {
        return null;
    }

    @Override
    protected void build(Block block) {
        Path path = toPath(block.getParts().get(1));
        location = language.createLocation(path);
    }
}
