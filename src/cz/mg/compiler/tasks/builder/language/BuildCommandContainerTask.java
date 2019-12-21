package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.language.commands.*;
import cz.mg.compiler.tasks.builder.utilities.Pattern;
import cz.mg.compiler.tasks.builder.utilities.Rule;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.*;
import static cz.mg.compiler.tasks.builder.utilities.Filter.*;


public abstract class BuildCommandContainerTask extends BlockBuildTask {
    private final Rules rules = new Rules(
            new Rule(new Pattern(_EXPRESSION_), this::buildPlainCommand),
            new Rule(new Pattern(IF, _ANY_VALUE_), this::buildIfCommand),
            new Rule(new Pattern(IF, _ANY_VALUE_, ALIAS, _NAME_), this::buildNamedIfCommand),
            new Rule(new Pattern(ELSE, IF, _ANY_VALUE_), this::buildElseIfCommand),
            new Rule(new Pattern(ELSE, IF, _ANY_VALUE_, ALIAS, _NAME_), this::buildNamedElseIfCommand),
            new Rule(new Pattern(ELSE), this::buildElseCommand),
            new Rule(new Pattern(ELSE, ALIAS, _NAME_), this::buildNamedElseCommand),
            new Rule(new Pattern(FOR, EACH, _DECLARATION_, IN, _ANY_VALUE_), this::buildForEachCommand),
            new Rule(new Pattern(FOR, EACH, _DECLARATION_, IN, _ANY_VALUE_, ALIAS, _NAME_), this::buildNamedForEachCommand),
            new Rule(new Pattern(WHILE, _ANY_VALUE_), this::buildWhileCommand),
            new Rule(new Pattern(WHILE, _ANY_VALUE_, ALIAS, _NAME_), this::buildNamedWhileCommand),
            new Rule(new Pattern(CONTINUE), this::buildContinueCommand),
            new Rule(new Pattern(CONTINUE, _NAME_), this::buildContinueNameCommand),
            new Rule(new Pattern(BREAK), this::buildBreakCommand),
            new Rule(new Pattern(BREAK, _NAME_), this::buildBreakNameCommand),
            new Rule(new Pattern(RETURN), this::buildReturnCommand),
            new Rule(new Pattern(RETURN, _EXPRESSION_), this::buildReturnExpressionCommand),
            new Rule(new Pattern(SWITCH, _ANY_VALUE_), this::buildSwitchCommand),
            new Rule(new Pattern(SWITCH, _ANY_VALUE_, ALIAS, _NAME_), this::buildNamedSwitchCommand)
    );

    public BuildCommandContainerTask(Block block, Context context) {
        super(block, context);
    }

    protected abstract Object getParent();

    @Override
    public Rules getRules() {
        return rules;
    }

    @Override
    protected abstract void build(Block block);

    protected void buildPlainCommand(Block block) {
        BuildPlainCommandTask task = new BuildPlainCommandTask(block, getContext());
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
        store(getParent(), "declaredVariables", task.getDeclaredVariables());
    }

    protected void buildIfCommand(Block block) {
        BuildIfCommandTask task = new BuildIfCommandTask(block, getContext(), false);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildNamedIfCommand(Block block) {
        BuildIfCommandTask task = new BuildIfCommandTask(block, getContext(), true);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildElseIfCommand(Block block) {
        BuildElseIfCommandTask task = new BuildElseIfCommandTask(block, getContext(), false);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildNamedElseIfCommand(Block block) {
        BuildElseIfCommandTask task = new BuildElseIfCommandTask(block, getContext(), true);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildElseCommand(Block block) {
        BuildElseCommandTask task = new BuildElseCommandTask(block, getContext(), false);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildNamedElseCommand(Block block) {
        BuildElseCommandTask task = new BuildElseCommandTask(block, getContext(), true);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildForEachCommand(Block block) {
        BuildForEachCommandTask task = new BuildForEachCommandTask(block, getContext(), false);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildNamedForEachCommand(Block block) {
        BuildForEachCommandTask task = new BuildForEachCommandTask(block, getContext(), true);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildWhileCommand(Block block) {
        BuildWhileCommandTask task = new BuildWhileCommandTask(block, getContext(), false);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildNamedWhileCommand(Block block) {
        BuildWhileCommandTask task = new BuildWhileCommandTask(block, getContext(), true);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildContinueCommand(Block block) {
        BuildContinueCommandTask task = new BuildContinueCommandTask(block, getContext(), false);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildContinueNameCommand(Block block) {
        BuildContinueCommandTask task = new BuildContinueCommandTask(block, getContext(), true);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildBreakCommand(Block block) {
        BuildBreakCommandTask task = new BuildBreakCommandTask(block, getContext(), false);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildBreakNameCommand(Block block) {
        BuildBreakCommandTask task = new BuildBreakCommandTask(block, getContext(), true);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildReturnCommand(Block block) {
        BuildReturnCommandTask task = new BuildReturnCommandTask(block, getContext(), false);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildReturnExpressionCommand(Block block) {
        BuildReturnCommandTask task = new BuildReturnCommandTask(block, getContext(), true);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    private void buildSwitchCommand(Block block) {
        BuildSwitchCommandTask task = new BuildSwitchCommandTask(block, getContext(), false);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    private void buildNamedSwitchCommand(Block block) {
        BuildSwitchCommandTask task = new BuildSwitchCommandTask(block, getContext(), true);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildCaseCommand(Block block) {
        BuildCaseCommandTask task = new BuildCaseCommandTask(block, getContext(), false);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }

    protected void buildNamedCaseCommand(Block block) {
        BuildCaseCommandTask task = new BuildCaseCommandTask(block, getContext(), true);
        task.tryToRun();
        store(getParent(), "commands", task.getCommand());
    }
}
