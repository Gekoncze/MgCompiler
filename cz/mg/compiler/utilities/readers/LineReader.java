package cz.mg.compiler.utilities.readers;

import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.Page;
import cz.mg.compiler.tasks.builder.BuildException;


public class LineReader extends ArrayReader<Line> {
    private final Page page;

    public LineReader(Page page) {
        this.page = page;
    }

    @Override
    protected int count() {
        return page.getLines().count();
    }

    @Override
    protected Line get(int i) {
        return page.getLines().get(i);
    }

    public Line readOptional(int indentation){
        if(canRead()){
            setMark();
            Line line = read();
            if(line.getIndentation() == indentation || isEmpty(line)) return line;
            back();
        }
        return null;
    }

    @Override
    protected CompileException outOfBoundsException() {
        if(getPosition() < 0){
            return new BuildException(page.getTrace().begin(), "Missing line.");
        } else {
            return new BuildException(page.getTrace().end(), "Missing line.");
        }
    }

    private boolean isEmpty(Line line){
        return line.getTokens().count() == 0 && line.getComment() == null;
    }
}
