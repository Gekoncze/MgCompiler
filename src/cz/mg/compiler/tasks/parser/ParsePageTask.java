package cz.mg.compiler.tasks.parser;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.Page;
import cz.mg.compiler.utilities.debug.Text;


public class ParsePageTask extends ParseTask {
    @Link
    private final Page page;

    @Part
    private final ChainList<ParseLineTask> parseLineTasks = new ChainList<>();

    public ParsePageTask(Page page) {
        this.page = page;
    }

    @Override
    protected void onRun() {
        for(Text lineText : page.getContent().splitByEach("\n")) {
            Line line = new Line(lineText);
            page.getLines().addLast(line);
            parseLineTasks.addLast(new ParseLineTask(line));
            parseLineTasks.getLast().tryToRun();
        }
    }
}
