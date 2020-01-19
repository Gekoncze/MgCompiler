package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.mg.LogicalMg;
import cz.mg.compiler.entities.logical.mg.Location;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.chains.Path;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.Rules;

import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.toPath;


public class BuildLocationTask extends BlockBuildTask {
    @Link
    private final LogicalMg logicalMg;

    @Link
    private Location location = null;

    public BuildLocationTask(Block block, LogicalMg logicalMg) {
        super(block, null);
        this.logicalMg = logicalMg;
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
        location = logicalMg.createLocation(path);
    }
}
