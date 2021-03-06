package cz.mg.compiler.tasks.builder;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.Documentation;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class BuildDocumentationTask extends BuildTask {
    @Link
    private final Block block;

    @Link
    private final Context context;

    @Link
    private Documentation documentation = null;

    public BuildDocumentationTask(Block block, Context context) {
        this.block = block;
        this.context = context;
    }

    public Documentation getDocumentation() {
        return documentation;
    }

    @Override
    protected void onRun() {
        if(block.getDocumentation() == null) return;
        if(block.getDocumentation().count() <= 0) return;
        documentation = new Documentation(textsTrace(block.getDocumentation()));
        for(Text text : block.getDocumentation()) documentation.getLines().addLast(text);
    }

    private static Trace textsTrace(ChainList<Text> texts){
        Text result = null;
        for(Text text : texts) result = Text.merge(result, text);
        return result.getTrace();
    }
}
