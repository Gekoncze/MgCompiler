package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.commands.ForEachCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Declaration;
import cz.mg.compiler.entities.structured.parts.Expression;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.tasks.builder.language.BuildCallTask;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.store;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.cast;


public class BuildForEachCommandTask extends BuildBlockCommandTask {
    private final boolean named;

    public BuildForEachCommandTask(Block block, Context context, boolean named) {
        super(block, context);
        this.named = named;
    }

    @Override
    protected void build(Block block) {
        Declaration declaration = cast(block.getParts().get(2), Declaration.class);
        BuildCallTask declarationTask = new BuildCallTask(declaration, getContext());
        declarationTask.tryToRun();

        Expression expression = cast(block.getParts().get(4), Expression.class);
        BuildCallTask task = new BuildCallTask(expression, getContext());
        task.tryToRun();

        command = new ForEachCommand(block.getTrace());
        command.setCall(task.getCall());
        store(command, "declaredVariables", declarationTask.getDeclaredVariables());
        store(command, "declaredVariables", task.getDeclaredVariables());

        if(named){
            Name name = cast(block.getParts().get(6), Name.class);
            ((ForEachCommand)command).setName(name.getContent());
        }
    }
}
