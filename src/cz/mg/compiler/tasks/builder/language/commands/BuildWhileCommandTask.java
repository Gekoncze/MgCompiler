package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.commands.WhileCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.tasks.builder.language.BuildCallTask;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.store;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildWhileCommandTask extends BuildBlockCommandTask {
    @Info
    private final boolean named;

    @Part
    private BuildCallTask buildConditionTask;

    public BuildWhileCommandTask(Block block, Context context, boolean named) {
        super(block, context);
        this.named = named;
    }

    @Override
    protected void build(Block block) {
        buildConditionTask = new BuildCallTask(block.getParts().get(1), getContext());
        buildConditionTask.tryToRun();
        command = new WhileCommand(block.getTrace());
        command.setCall(buildConditionTask.getCall());
        store(command, "declaredVariables", buildConditionTask.getDeclaredVariables());

        if(named){
            Name name = cast(block.getParts().get(3), Name.class);
            ((WhileCommand)command).setName(name.getContent());
        }
    }
}
