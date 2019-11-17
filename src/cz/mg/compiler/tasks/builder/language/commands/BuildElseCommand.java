package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.commands.ElseCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.tasks.Task;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildElseCommand extends BuildBlockCommandTask {
    private final boolean named;

    public BuildElseCommand(Task parentTask, Block block, Context context, boolean named) {
        super(parentTask, block, context);
        this.named = named;
    }

    @Override
    protected void build(Block block) {
        command = new ElseCommand(block.getTrace());

        if(named){
            Name name = cast(block.getParts().get(2), Name.class);
            ((ElseCommand)command).setName(name.getContent());
        }
    }
}
