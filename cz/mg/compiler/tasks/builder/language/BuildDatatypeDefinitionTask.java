package cz.mg.compiler.tasks.builder.language;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.definitions.DatatypeDefinition;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.utilities.Pattern;
import cz.mg.compiler.tasks.builder.utilities.Rule;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.*;
import static cz.mg.compiler.tasks.builder.utilities.Filter.*;


public abstract class BuildDatatypeDefinitionTask extends BlockBuildTask {
    protected final Rule RULE_F1 = new Rule(new Pattern(FUNCTION, _NAME_), this::buildFunction);
    protected final Rule RULE_F2 = new Rule(new Pattern(FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_), this::buildFunctionI);
    protected final Rule RULE_F3 = new Rule(new Pattern(FUNCTION, _NAME_, OUTPUT, _DECLARATION_LIST_), this::buildFunctionO);
    protected final Rule RULE_F4 = new Rule(new Pattern(FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_, OUTPUT, _DECLARATION_LIST_), this::buildFunctionIO);
    protected final Rule RULE_F5 = new Rule(new Pattern(FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_, OUTPUT, _DECLARATION_LIST_, OPERATOR, _OPERATOR_), this::buildFunctionIOO);
    protected final Rule RULE_P1 = new Rule(new Pattern(_DECLARATION_LIST_), this::buildProperties);
    protected final Rule RULE_P2 = new Rule(new Pattern(_EXPRESSION_), this::buildProperties);

    protected final Rules rulesLeft = new Rules(RULE_F1, RULE_F2, RULE_F3, RULE_F4, RULE_F5, RULE_P1, RULE_P2);
    protected final Rules rulesRight = new Rules(RULE_F1, RULE_F2, RULE_F3, RULE_F4, RULE_F5);

    public BuildDatatypeDefinitionTask(Task parentTask, Block block, Context context) {
        super(parentTask, block, context);
    }

    protected void buildFunction(Block block) {
        BuildFunctionTask task = new BuildFunctionTask(this, block, getContext(), false, false, false);
        task.tryToRun();
        store(getDefinition(), "functions", task.getFunctionDefinition());
    }

    protected void buildFunctionI(Block block) {
        BuildFunctionTask task = new BuildFunctionTask(this, block, getContext(), true, false, false);
        task.tryToRun();
        store(getDefinition(), "functions", task.getFunctionDefinition());
    }

    protected void buildFunctionO(Block block) {
        BuildFunctionTask task = new BuildFunctionTask(this, block, getContext(), false, true, false);
        task.tryToRun();
        store(getDefinition(), "functions", task.getFunctionDefinition());
    }

    protected void buildFunctionIO(Block block) {
        BuildFunctionTask task = new BuildFunctionTask(this, block, getContext(), true, true, false);
        task.tryToRun();
        store(getDefinition(), "functions", task.getFunctionDefinition());
    }

    protected void buildFunctionIOO(Block block) {
        BuildFunctionTask task = new BuildFunctionTask(this, block, getContext(), true, true, true);
        task.tryToRun();
        store(getDefinition(), "functions", task.getFunctionDefinition());
    }

    protected void buildProperties(Block block) {
        BuildPropertiesTask task = new BuildPropertiesTask(this, block, getContext());
        task.tryToRun();
        store(getDefinition(), "properties", task.getProperties());
    }

    protected abstract DatatypeDefinition getDefinition();
}
