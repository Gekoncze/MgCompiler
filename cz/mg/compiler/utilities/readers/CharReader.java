package cz.mg.compiler.utilities.readers;

import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.tasks.parser.ParseException;


public class CharReader extends ArrayReader<Character> {
    private final Text text;

    public CharReader(Text text) {
        this.text = text;
    }

    public Text slice(){
        return text.slice(getMark(), getPosition());
    }

    public Text slice(int deltaBegin, int deltaEnd){
        return text.slice(getMark() + deltaBegin, getPosition() + deltaEnd);
    }

    public Text sliceChar(){
        return text.slice(getPosition()-1, getPosition());
    }

    @Override
    protected int count() {
        return text.count();
    }

    @Override
    protected Character get(int i) {
        return text.get(i);
    }

    public boolean hasNext(char ch) {
        if(!canRead()) return false;
        return text.get(getPosition() + 1) == ch;
    }

    @Override
    protected CompileException outOfBoundsException() {
        if(getPosition() < 0) {
            return new ParseException(text.getTrace().begin(), "Missing character.");
        } else {
            return new ParseException(text.getTrace().end(), "Missing character.");
        }
    }
}
