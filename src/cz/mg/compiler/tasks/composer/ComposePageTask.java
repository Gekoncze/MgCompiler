package cz.mg.compiler.tasks.composer;

import cz.mg.compiler.entities.structured.Container;
import cz.mg.compiler.entities.text.Page;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.utilities.readers.LineReader;


public class ComposePageTask extends ComposeTask {
    private final Page page;
    private final Container structuredPage;

    public ComposePageTask(Task parentTask, Page page, Container structuredPage) {
        super(parentTask);
        this.page = page;
        this.structuredPage = structuredPage;
    }

    @Override
    protected void onRun() {
        LineReader lineReader = new LineReader(page);
        new ComposeBlocksTask(this, lineReader, structuredPage.getBlocks(), 0).run();
    }
}
