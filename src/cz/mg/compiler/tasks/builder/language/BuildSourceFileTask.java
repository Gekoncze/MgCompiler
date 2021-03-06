package cz.mg.compiler.tasks.builder.language;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.Language;
import cz.mg.compiler.entities.logical.language.Location;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Container;
import cz.mg.compiler.tasks.builder.BlockBuildTask;
import cz.mg.compiler.tasks.builder.BuildException;
import cz.mg.compiler.tasks.builder.BuildTask;
import cz.mg.compiler.tasks.builder.language.natives.BuildNativeFunctionTask;
import cz.mg.compiler.tasks.builder.language.natives.BuildNativeStructureDefinitionTask;
import cz.mg.compiler.tasks.builder.language.natives.BuildNativeTypeDefinitionTask;
import cz.mg.compiler.tasks.builder.language.natives.BuildNativeUsingTask;
import cz.mg.compiler.tasks.builder.utilities.Pattern;
import cz.mg.compiler.tasks.builder.utilities.Rule;
import cz.mg.compiler.tasks.builder.utilities.Rules;
import cz.mg.compiler.utilities.debug.PlaceholderText;
import static cz.mg.compiler.tasks.builder.utilities.BuildUtilities.*;
import static cz.mg.compiler.tasks.builder.utilities.Filter.*;
import static cz.mg.compiler.entities.logical.language.definitions.DatatypeDefinition.Inheritance;


public class BuildSourceFileTask extends BlockBuildTask {
    private final Rules rules = new Rules(
            new Rule(new Pattern(LOCATION, _NAME_PATH_), this::buildLocation),
            new Rule(new Pattern(USING, _NAME_PATH_), this::buildUsingDirect),
            new Rule(new Pattern(USING, _NAME_PATH_, ALIAS, _NAME_), this::buildUsingAlias),
            new Rule(new Pattern(USING, ALL, _NAME_PATH_), this::buildUsingAll),
            new Rule(new Pattern(CLASS, _NAME_), this::buildClass),
            new Rule(new Pattern(CLASS, _NAME_, IS, _NAME_LIST_), this::buildClassIs),
            new Rule(new Pattern(CLASS, _NAME_, ALIAS, _NAME_), this::buildClassAlias),
            new Rule(new Pattern(CLASS, _NAME_, VIEW, _NAME_LIST_), this::buildClassView),
            new Rule(new Pattern(CLASS, _NAME_, LIKE, _NAME_LIST_), this::buildClassLike),
            new Rule(new Pattern(STRUCTURE, _NAME_), this::buildStructure),
            new Rule(new Pattern(STRUCTURE, _NAME_, IS, _NAME_), this::buildStructureIs),
            new Rule(new Pattern(STRUCTURE, _NAME_, ALIAS, _NAME_), this::buildStructureAlias),
            new Rule(new Pattern(STRUCTURE, _NAME_, VIEW, _NAME_), this::buildStructureView),
            new Rule(new Pattern(STRUCTURE, _NAME_, LIKE, _NAME_), this::buildStructureLike),
            new Rule(new Pattern(TYPE, _NAME_), this::buildType),
            new Rule(new Pattern(TYPE, _NAME_, IS, _NAME_), this::buildTypeIs),
            new Rule(new Pattern(TYPE, _NAME_, ALIAS, _NAME_), this::buildTypeAlias),
            new Rule(new Pattern(TYPE, _NAME_, VIEW, _NAME_), this::buildTypeView),
            new Rule(new Pattern(TYPE, _NAME_, LIKE, _NAME_), this::buildTypeLike),
            new Rule(new Pattern(FUNCTION, _NAME_), this::buildFunction),
            new Rule(new Pattern(FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_), this::buildFunctionI),
            new Rule(new Pattern(FUNCTION, _NAME_, OUTPUT, _DECLARATION_LIST_), this::buildFunctionO),
            new Rule(new Pattern(FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_, OUTPUT, _DECLARATION_LIST_), this::buildFunctionIO),
            new Rule(new Pattern(FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_, OUTPUT, _DECLARATION_LIST_, OPERATOR, _OPERATOR_), this::buildFunctionIOO),
            new Rule(new Pattern(NATIVE, USING, _VALUE_), this::buildNativeUsing),
            new Rule(new Pattern(NATIVE, TYPE, _NAME_), this::buildNativeType),
            new Rule(new Pattern(NATIVE, STRUCTURE, _NAME_), this::buildNativeStructure),
            new Rule(new Pattern(NATIVE, FUNCTION, _NAME_), this::buildNativeFunction),
            new Rule(new Pattern(NATIVE, FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_), this::buildNativeFunctionI),
            new Rule(new Pattern(NATIVE, FUNCTION, _NAME_, OUTPUT, _DECLARATION_LIST_), this::buildNativeFunctionO),
            new Rule(new Pattern(NATIVE, FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_, OUTPUT, _DECLARATION_), this::buildNativeFunctionIO),
            new Rule(new Pattern(NATIVE, FUNCTION, _NAME_, INPUT, _DECLARATION_LIST_, OUTPUT, _DECLARATION_, OPERATOR, _OPERATOR_), this::buildNativeFunctionIOO)
    );

    @Link
    private final Language language;

    @Link
    private Location location = null;

    @Link
    private Context context = null;

    @Part
    private final ChainList<BuildTask> buildTasks = new ChainList<>();

    public BuildSourceFileTask(Container page, Language language) {
        super(page, null);
        this.language = language;
    }

    @Override
    public Rules getRules() {
        return rules;
    }

    @Override
    protected void build(Block block) {
    }

    private void buildLocation(Block block) {
        if(location != null) throw new BuildException(block, "Location was already defined ", new PlaceholderText(location, "here"), ".");
        BuildLocationTask task = new BuildLocationTask(block, language);
        buildTasks.addLast(task);
        task.run();
        location = task.getLocation();
        context = new Context(location);
        store(location, "entities", context);
    }

    private void checkLocation(Block block) {
        if(location == null) throw new BuildException(block, "Missing location specification. It must be the first block in the file.");
    }

    private void buildUsingDirect(Block block) {
        checkLocation(block);
        BuildUsingDirectTask task = new BuildUsingDirectTask(block, context);
        buildTasks.addLast(task);
        task.tryToRun();
        store(context, "usings", task.getUsing());
    }

    private void buildUsingAlias(Block block) {
        checkLocation(block);
        BuildUsingAliasTask task = new BuildUsingAliasTask(block, context);
        buildTasks.addLast(task);
        task.tryToRun();
        store(context, "usings", task.getUsing());
    }

    private void buildUsingAll(Block block) {
        checkLocation(block);
        BuildUsingAllTask task = new BuildUsingAllTask(block, context);
        buildTasks.addLast(task);
        task.tryToRun();
        store(context, "usings", task.getUsing());
    }

    private void buildClass(Block block) {
        checkLocation(block);
        BuildClassDefinitionTask task = new BuildClassDefinitionTask(block, context, null);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildClassIs(Block block) {
        checkLocation(block);
        BuildClassDefinitionTask task = new BuildClassDefinitionTask(block, context, Inheritance.IS);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildClassAlias(Block block) {
        checkLocation(block);
        BuildClassDefinitionTask task = new BuildClassDefinitionTask(block, context, Inheritance.ALIAS);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildClassView(Block block) {
        checkLocation(block);
        BuildClassDefinitionTask task = new BuildClassDefinitionTask(block, context, Inheritance.VIEW);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildClassLike(Block block) {
        checkLocation(block);
        BuildClassDefinitionTask task = new BuildClassDefinitionTask(block, context, Inheritance.LIKE);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildStructure(Block block) {
        checkLocation(block);
        BuildStructureDefinitionTask task = new BuildStructureDefinitionTask(block, context, null);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildStructureIs(Block block) {
        checkLocation(block);
        BuildStructureDefinitionTask task = new BuildStructureDefinitionTask(block, context, Inheritance.IS);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildStructureAlias(Block block) {
        checkLocation(block);
        BuildStructureDefinitionTask task = new BuildStructureDefinitionTask(block, context, Inheritance.ALIAS);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildStructureView(Block block) {
        checkLocation(block);
        BuildStructureDefinitionTask task = new BuildStructureDefinitionTask(block, context, Inheritance.VIEW);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildStructureLike(Block block) {
        checkLocation(block);
        BuildStructureDefinitionTask task = new BuildStructureDefinitionTask(block, context, Inheritance.LIKE);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildType(Block block) {
        checkLocation(block);
        BuildTypeDefinitionTask task = new BuildTypeDefinitionTask(block, context, null);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildTypeIs(Block block) {
        checkLocation(block);
        BuildTypeDefinitionTask task = new BuildTypeDefinitionTask(block, context, Inheritance.IS);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildTypeAlias(Block block) {
        checkLocation(block);
        BuildTypeDefinitionTask task = new BuildTypeDefinitionTask(block, context, Inheritance.ALIAS);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildTypeView(Block block) {
        checkLocation(block);
        BuildTypeDefinitionTask task = new BuildTypeDefinitionTask(block, context, Inheritance.VIEW);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildTypeLike(Block block) {
        checkLocation(block);
        BuildTypeDefinitionTask task = new BuildTypeDefinitionTask(block, context, Inheritance.LIKE);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    protected void buildFunction(Block block) {
        checkLocation(block);
        BuildFunctionTask task = new BuildFunctionTask(block, getContext(), false, false, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getFunctionDefinition());
    }

    protected void buildFunctionI(Block block) {
        checkLocation(block);
        BuildFunctionTask task = new BuildFunctionTask(block, getContext(), true, false, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getFunctionDefinition());
    }

    protected void buildFunctionO(Block block) {
        checkLocation(block);
        BuildFunctionTask task = new BuildFunctionTask(block, getContext(), false, true, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getFunctionDefinition());
    }

    protected void buildFunctionIO(Block block) {
        checkLocation(block);
        BuildFunctionTask task = new BuildFunctionTask(block, getContext(), true, true, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getFunctionDefinition());
    }

    protected void buildFunctionIOO(Block block) {
        checkLocation(block);
        BuildFunctionTask task = new BuildFunctionTask(block, getContext(), true, true, true);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getFunctionDefinition());
    }

    private void buildNativeUsing(Block block) {
        checkLocation(block);
        BuildNativeUsingTask task = new BuildNativeUsingTask(block, context);
        buildTasks.addLast(task);
        task.tryToRun();
        store(context, "usings", task.getUsing());
    }

    private void buildNativeType(Block block) {
        checkLocation(block);
        BuildNativeTypeDefinitionTask task = new BuildNativeTypeDefinitionTask(block, context);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    private void buildNativeStructure(Block block) {
        checkLocation(block);
        BuildNativeStructureDefinitionTask task = new BuildNativeStructureDefinitionTask(block, context);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getDefinition());
    }

    protected void buildNativeFunction(Block block) {
        checkLocation(block);
        BuildNativeFunctionTask task = new BuildNativeFunctionTask(block, getContext(), false, false, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getFunctionDefinition());
    }

    protected void buildNativeFunctionI(Block block) {
        checkLocation(block);
        BuildNativeFunctionTask task = new BuildNativeFunctionTask(block, getContext(), true, false, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getFunctionDefinition());
    }

    protected void buildNativeFunctionO(Block block) {
        checkLocation(block);
        BuildNativeFunctionTask task = new BuildNativeFunctionTask(block, getContext(), false, true, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getFunctionDefinition());
    }

    protected void buildNativeFunctionIO(Block block) {
        checkLocation(block);
        BuildNativeFunctionTask task = new BuildNativeFunctionTask(block, getContext(), true, true, false);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getFunctionDefinition());
    }

    protected void buildNativeFunctionIOO(Block block) {
        checkLocation(block);
        BuildNativeFunctionTask task = new BuildNativeFunctionTask(block, getContext(), true, true, true);
        buildTasks.addLast(task);
        task.tryToRun();
        store(location, "entities", task.getFunctionDefinition());
    }
}
