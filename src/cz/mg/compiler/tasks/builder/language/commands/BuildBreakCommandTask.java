package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.commands.BreakCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Name;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildBreakCommandTask extends BuildCommandTask {
    @Info
    private final boolean name;

    public BuildBreakCommandTask(Block block, Context context, boolean name) {
        super(block, context);
        this.name = name;
    }

    @Override
    protected void build(Block block) {
        if(name){
            Name name = cast(block.getParts().get(1), Name.class);
            command = new BreakCommand(block.getTrace(), name.getContent());
        } else {
            command = new BreakCommand(block.getTrace(), null);
        }
    }
}
