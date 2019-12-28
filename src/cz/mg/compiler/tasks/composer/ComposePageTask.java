package cz.mg.compiler.tasks.composer;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.structured.Container;
import cz.mg.compiler.entities.text.Page;
import cz.mg.compiler.utilities.readers.LineReader;


public class ComposePageTask extends ComposeTask {
    @Link
    private final Page page;

    @Link
    private final Container structuredPage;

    @Part
    private ComposeBlocksTask composeBlocksTask;

    public ComposePageTask(Page page, Container structuredPage) {
        this.page = page;
        this.structuredPage = structuredPage;
    }

    @Override
    protected void onRun() {
        LineReader lineReader = new LineReader(page);
        composeBlocksTask = new ComposeBlocksTask(lineReader, structuredPage.getBlocks(), 0);
        composeBlocksTask.run();

    }
}
