package cz.mg.compiler.tasks.a.parser;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Book;
import cz.mg.compiler.entities.a.segments.Page;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.entities.a.segments.Segment;
import cz.mg.compiler.entities.resources.Stream;
import cz.mg.compiler.tasks.CompileException;
import cz.mg.compiler.tasks.Task;


public class PageParser extends Parser<Task, LineParser, Stream, Page> {
    private Segment currentParentSegment = null;
    private int currentParentChildIndentation = 0;

    public PageParser(Task parentTask, Stream stream, Page page) {
		super(parentTask, stream, page);
    }

    @Override
    protected void onRun() {
        getOutput().setName(getInput().getName());
        this.currentParentSegment = getOutput();
        
        StreamReader reader = null;
        try {
            reader = new StreamReader(getInput());
            String line;
            int i = 1;
            while((line = reader.readLine()) != null){
                addLine(line, i);
                i++;
            }
        } finally {
            if(reader != null) reader.close();
        }
    }
    
    public void addLine(String line, int lineNumber) {
        int currentIndentation = getIndentation(line);
        Location lineLocation = new Location(getInput().getName(), lineNumber, lineNumber, 0, line.length());
        tryRaise(currentIndentation, lineLocation);
        trySink(currentIndentation, lineLocation);
        if(isEmpty(line)){
            new LineParser(this, "", new Line(currentParentSegment, lineLocation)).run();
        } else {
            new LineParser(this, line.substring(currentIndentation), new Line(currentParentSegment, lineLocation)).run();
        }
    }
    
    private void tryRaise(int currentIndentation, Location lineLocation){
        while(currentIndentation < currentParentChildIndentation){
            Segment parent = (Segment) currentParentSegment.getParent();
            if(parent instanceof Book) throw new RuntimeException();
            currentParentSegment = parent;
            currentParentChildIndentation--;
        }
    }
    
    private void trySink(int currentIndentation, Location lineLocation){
        int difference = currentIndentation - currentParentChildIndentation;
        if(difference > 0){
            if(difference > 1) throw new CompileException(lineLocation, "Invalid indentation. Difference in indentation between lines is too big " + difference + ".");
            Segment lastChildSegment = (Segment) currentParentSegment.getChildren().getLast();
            if(lastChildSegment == null) throw new CompileException(lineLocation, "Invalid indentation. Missing parent line.");
            currentParentSegment = lastChildSegment;
            currentParentChildIndentation = currentIndentation;
        }
    }
    
    private int getIndentation(String line){
        if(isEmpty(line)) return currentParentChildIndentation;
        for(int i = 0; i < line.length(); i++) if(line.charAt(i) != '\t') return i;
        return 0;
    }
    
    private boolean isEmpty(String line){
        return line.trim().length() <= 0;
    }
}
