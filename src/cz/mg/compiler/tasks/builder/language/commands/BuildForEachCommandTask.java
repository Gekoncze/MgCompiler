package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Part;
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
    @Info
    private final boolean named;

    @Part
    private BuildCallTask buildDeclarationTask;

    @Part
    private BuildCallTask buildExpressionTask;

    public BuildForEachCommandTask(Block block, Context context, boolean named) {
        super(block, context);
        this.named = named;
    }

    @Override
    protected void build(Block block) {
        Declaration declaration = cast(block.getParts().get(2), Declaration.class);
        buildDeclarationTask = new BuildCallTask(declaration, getContext());
        buildDeclarationTask.tryToRun();

        Expression expression = cast(block.getParts().get(4), Expression.class);
        buildExpressionTask = new BuildCallTask(expression, getContext());
        buildExpressionTask.tryToRun();

        command = new ForEachCommand(block.getTrace());
        command.setCall(buildExpressionTask.getCall());
        store(command, "declaredVariables", buildDeclarationTask.getDeclaredVariables());
        store(command, "declaredVariables", buildExpressionTask.getDeclaredVariables());

        if(named){
            Name name = cast(block.getParts().get(6), Name.class);
            ((ForEachCommand)command).setName(name.getContent());
        }
    }
}
