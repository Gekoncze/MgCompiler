package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.language.Language;
import cz.mg.compiler.entities.logical.language.Location;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.chains.Path;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.Rules;

import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.toPath;


public class BuildLocationTask extends BlockBuildTask {
    @Link
    private final Language language;

    @Link
    private Location location = null;

    public BuildLocationTask(Block block, Language language) {
        super(block, null);
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
