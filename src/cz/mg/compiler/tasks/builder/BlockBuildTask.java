package cz.mg.compiler.tasks.builder;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.Documentation;
import cz.mg.compiler.entities.logical.language.Stamps;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.utilities.Rules;


public abstract class BlockBuildTask extends BuildTask {
    private final Block block;
    private final Context context;
    private Stamps stamps = null;
    private Documentation documentation = null;

    public BlockBuildTask(Task parentTask, Block block, Context context) {
        super(parentTask);
        this.block = block;
        this.context = context;
    }

    public BlockBuildTask(Task parentTask, Block block) {
        super(parentTask);
        this.block = block;
        this.context = null;
    }

    public Context getContext() {
        return context;
    }

    @Override
    protected void onRun() {
        this.stamps = buildStamps(block, context);
        this.documentation = buildDocumentation(block, context);

        build(block);

        if(stamps != null) getErrors().addLast(new BuildException(block, "Block expects no stamps, given ", block.getStamps().count(), "."));
        if(documentation != null) getErrors().addLast(new BuildException(block, "Block expects no documentation lines, given ", block.getDocumentation().count(), "."));

        if(block.getBlocks().count() > 0){
            Rules rules = getRules();
            if(rules != null){
                rules.match(this, block.getBlocks());
            } else {
                throw new BuildException(block, "Block expects no child blocks, given ", block.getBlocks().count(), ".");
            }
        }
    }

    protected abstract Rules getRules();

    protected abstract void build(Block block);

    public Stamps takeStamps(){
        Stamps s = stamps;
        stamps = null;
        return s;
    }

    public Documentation takeDocumentation(){
        Documentation d = documentation;
        documentation = null;
        return d;
    }

    private Stamps buildStamps(Block block, Context context){
        BuildStampsTask task = new BuildStampsTask(this, block, context);
        task.tryToRun();
        return task.getStamps();
    }

    private Documentation buildDocumentation(Block block, Context context){
        BuildDocumentationTask task = new BuildDocumentationTask(this, block, context);
        task.tryToRun();
        return task.getDocumentation();
    }
}
