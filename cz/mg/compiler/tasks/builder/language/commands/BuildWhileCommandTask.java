package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.commands.WhileCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.language.BuildCallTask;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.store;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildWhileCommandTask extends BuildBlockCommandTask {
    private final boolean named;

    public BuildWhileCommandTask(Task parentTask, Block block, Context context, boolean named) {
        super(parentTask, block, context);
        this.named = named;
    }

    @Override
    protected void build(Block block) {
        BuildCallTask task = new BuildCallTask(this, block.getParts().get(1), getContext());
        task.tryToRun();
        command = new WhileCommand(block.getTrace());
        command.setCall(task.getCall());
        store(command, "declaredVariables", task.getDeclaredVariables());

        if(named){
            Name name = cast(block.getParts().get(3), Name.class);
            ((WhileCommand)command).setName(name.getContent());
        }
    }
}
