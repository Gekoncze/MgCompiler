package cz.mg.compiler.tasks.builder.language.commands;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.commands.SwitchCommand;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.language.BuildCallTask;
import cz.mg.compiler.tasks.builder.utilities.Pattern;
import cz.mg.compiler.tasks.builder.utilities.Rule;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.*;
import static cz.mg.compiler.tasks.builder.utilities.Filter.*;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.*;


public class BuildSwitchCommandTask extends BuildBlockCommandTask {
    private final Rules rules = new Rules(
            new Rule(new Pattern(CASE, _ANY_VALUE_), this::buildCaseCommand),
            new Rule(new Pattern(CASE, _ANY_VALUE_, ALIAS, _NAME_), this::buildNamedCaseCommand),
            new Rule(new Pattern(ELSE), this::buildElseCommand)
    );

    private final boolean named;

    public BuildSwitchCommandTask(Task parentTask, Block block, Context context, boolean named) {
        super(parentTask, block, context);
        this.named = named;
    }

    @Override
    public Rules getRules() {
        return rules;
    }

    @Override
    protected void build(Block block) {
        BuildCallTask task = new BuildCallTask(this, block.getParts().get(1), getContext());
        task.tryToRun();
        command = new SwitchCommand(block.getTrace());
        command.setCall(task.getCall());
        store(command, "declaredVariables", task.getDeclaredVariables());

        if(named){
            Name name = cast(block.getParts().get(3), Name.class);
            ((SwitchCommand)command).setName(name.getContent());
        }
    }
}
