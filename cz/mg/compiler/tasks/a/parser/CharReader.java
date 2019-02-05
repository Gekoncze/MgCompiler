package cz.mg.compiler.tasks.a.parser;

import cz.mg.temp.readers.ArrayReader;
import cz.mg.compiler.Location;
import cz.mg.compiler.entities.a.segments.Line;
import cz.mg.compiler.tasks.CompileException;


public class CharReader extends ArrayReader<Character> {
    private final String text;
    private final Line line;

    public CharReader(String text, Line line) {
        this.text = text;
        this.line = line;
    }
    
    public String getSubstring(){
        return text.substring(getMark(), getPosition() + 1);
    }
    
    public Location getSubstringLocation(){
        return new Location(
            line.getLocation().getName(),
            line.getLocation().getRowBegin(),
            line.getLocation().getRowEnd(),
            getMark(),
            getPosition()+1
        );
    }
    
    public String getSubstring(int deltaBegin, int deltaEnd){
        return text.substring(getMark() + deltaBegin, getPosition() + 1 + deltaEnd);
    }
	
	public String getCharSubstring(){
        return text.substring(getPosition(), getPosition() + 1);
    }

    @Override
    protected int size() {
        return text.length();
    }

    @Override
    protected Character get(int i) {
        return text.charAt(i);
    }

	public boolean hasNext(char ch) {
		if(!hasNext()) return false;
		return text.charAt(getPosition() + 1) == ch;
	}

    @Override
    protected CompileException outOfBoundsException() {
        return new CompileException(line, "Missing character.");
    }
}
