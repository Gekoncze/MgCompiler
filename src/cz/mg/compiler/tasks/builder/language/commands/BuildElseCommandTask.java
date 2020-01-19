package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.commands.ElseCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Name;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildElseCommandTask extends BuildBlockCommandTask {
    @Info
    private final boolean named;

    public BuildElseCommandTask(Block block, Context context, boolean named) {
        super(block, context);
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
