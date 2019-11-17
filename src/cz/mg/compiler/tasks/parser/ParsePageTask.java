package cz.mg.compiler.tasks.parser;

import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.Page;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.utilities.debug.Text;


public class ParsePageTask extends ParseTask {
    private final Page page;

    public ParsePageTask(Task parentTask, Page page) {
        super(parentTask);
        this.page = page;
    }

    @Override
    protected void onRun() {
        for(Text lineText : page.getContent().splitByEach("\n")) {
            Line line = new Line(lineText);
            page.getLines().addLast(line);
            new ParseLineTask(this, line).tryToRun();
        }
    }
}
