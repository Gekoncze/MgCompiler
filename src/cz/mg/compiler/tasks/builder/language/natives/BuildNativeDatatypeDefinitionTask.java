package cz.mg.compiler.tasks.builder.language.natives;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.natives.NativeDatatypeDefinition;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.BuildTask;
import cz.mg.compiler.tasks.builder.language.BuildVariablesTask;
import cz.mg.compiler.tasks.builder.utilities.Pattern;
import cz.mg.compiler.tasks.builder.utilities.Rule;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.store;
import static cz.mg.compiler.tasks.builder.utilities.Filter.*;


public abstract class BuildNativeDatatypeDefinitionTask extends BlockBuildTask {
    protected final Rule RULE_F1 = new Rule(new Pattern(NATIVE, FUNCTION, _NAME_), this::buildNativeFunction);
    protected final Rule RULE_F2 = new Rule(new Pattern(NATIVE, FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_), this::buildNativeFunctionI);
    protected final Rule RULE_F3 = new Rule(new Pattern(NATIVE, FUNCTION, _NAME_, OUTPUT, _DECLARATION_LIST_), this::buildNativeFunctionO);
    protected final Rule RULE_F4 = new Rule(new Pattern(NATIVE, FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_, OUTPUT, _DECLARATION_), this::buildNativeFunctionIO);
    protected final Rule RULE_F5 = new Rule(new Pattern(NATIVE, FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_, OUTPUT, _DECLARATION_, OPERATOR, _OPERATOR_), this::buildNativeFunctionIOO);
    protected final Rule RULE_P1 = new Rule(new Pattern(_DECLARATION_LIST_), this::buildProperties);

    protected final Rules rulesLeft = new Rules(RULE_F1, RULE_F2, RULE_F3, RULE_F4, RULE_F5, RULE_P1);
    protected final Rules rulesRight = new Rules(RULE_F1, RULE_F2, RULE_F3, RULE_F4, RULE_F5);

    @Part
    private final ChainList<BuildTask> buildTasks = new ChainList<>();

    public BuildNativeDatatypeDefinitionTask(Block block, Context context) {
        super(block, context);
    }

    protected void buildNativeFunction(Block block) {
        BuildNativeFunctionTask task = new BuildNativeFunctionTask(block, getContext(), false, false, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(getDefinition(), "functions", task.getFunctionDefinition());
    }

    protected void buildNativeFunctionI(Block block) {
        BuildNativeFunctionTask task = new BuildNativeFunctionTask(block, getContext(), true, false, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(getDefinition(), "functions", task.getFunctionDefinition());
    }

    protected void buildNativeFunctionO(Block block) {
        BuildNativeFunctionTask task = new BuildNativeFunctionTask(block, getContext(), false, true, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(getDefinition(), "functions", task.getFunctionDefinition());
    }

    protected void buildNativeFunctionIO(Block block) {
        BuildNativeFunctionTask task = new BuildNativeFunctionTask(block, getContext(), true, true, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(getDefinition(), "functions", task.getFunctionDefinition());
    }

    protected void buildNativeFunctionIOO(Block block) {
        BuildNativeFunctionTask task = new BuildNativeFunctionTask(block, getContext(), true, true, true);
        buildTasks.addLast(task);
        task.tryToRun();
        store(getDefinition(), "functions", task.getFunctionDefinition());
    }

    protected void buildProperties(Block block) {
        BuildVariablesTask task = new BuildVariablesTask(block, getContext());
        buildTasks.addLast(task);
        task.tryToRun();
        store(getDefinition(), "properties", task.getVariables());
    }

    protected abstract NativeDatatypeDefinition getDefinition();
}
