package cz.mg.compiler.tasks.parser;

import cz.mg.compiler.entities.text.Line;
import cz.mg.compiler.entities.text.Token;
import cz.mg.compiler.entities.text.tokens.SpecialToken;
import cz.mg.compiler.entities.text.tokens.ValueToken;
import cz.mg.compiler.entities.text.tokens.WordToken;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.readers.CharReader;


public class ParseLineTask extends ParseTask {
    private static int INDENTATION_SIZE = 4;
    private static final char[] SPECIALS = new char[]{
            '(', ')', '[', ']', '{', '}',
            ':', ',', '.',
            '@', '&', '$',

            '+', '-',
            '*', '/', '\\', '%',
            '^', '!',
            '<', '>', '=',

            '~', '°', ';', '\'', '|', '?',
    };

    private final Line line;
    private CharReader reader = null;

    public ParseLineTask(Task parentTask, Line line) {
        super(parentTask);
        this.line = line;
    }

    @Override
    protected void onRun() {
        if(parseEmptyLine()) return;

        reader = new CharReader(line.getContent());
        line.setIndentation(countIndentation(reader));

        while(reader.canRead()){
            reader.setMark();
            char ch = reader.read();

            if(ch == ' '){
                continue;
            } else if(isComment(ch)){
                line.setComment(parseComment());
            } else if(isValue(ch)){
                line.getTokens().addLast(parseValue(ch));
            } else if(isWord(ch)){
                line.getTokens().addLast(parseWord());
            } else if(isSpecial(ch)) {
                line.getTokens().addLast(parseSpecial());
            } else {
                throw new ParseException(reader.sliceChar(), "Illegal character ", reader.sliceChar(), " (", (int)ch, ").");
            }
        }
    }

    protected boolean parseEmptyLine(){
        return line.getContent().trim().count() <= 0;
    }

    protected int countIndentation(CharReader reader){
        reader.setMark();
        while(reader.canRead()){
            char ch = reader.read();
            if(ch != ' ') {
                reader.back();
                break;
            }
        }
        Text indentation = reader.slice();
        if(indentation.count() % INDENTATION_SIZE != 0) throw new ParseException(indentation, "Illgal indentation. Expected indentation by ", INDENTATION_SIZE, " spaces. Found total of ", indentation.count(), " spaces in indentation.");
        return indentation.count() / INDENTATION_SIZE;
    }

    protected boolean isComment(char ch){
        return ch == '#';
    }

    protected boolean isValue(char ch){
        return ch == '"' || ch == '\'';
    }

    protected boolean isWord(char ch){
        return isLowerCharacter(ch) || isUpperCharacter(ch) || isNumber(ch) || ch == '_';
    }

    protected boolean isSpecial(char ch){
        for(char s : SPECIALS) if(ch == s) return true;
        return false;
    }

    protected boolean isNumber(char ch){
        return ch >= '0' && ch <= '9';
    }

    protected boolean isLowerCharacter(char ch){
        return (ch >= 'a' && ch <= 'z');
    }

    protected boolean isUpperCharacter(char ch){
        return (ch >= 'A' && ch <= 'Z');
    }

    protected boolean isAllowedLiteralCharacter(char ch){
        return (ch >= ' ' && ch <= '~');
    }

    protected boolean isAllowedCommentCharacter(char ch){
        return (ch >= ' ' && ch <= '~');
    }

    protected Text parseComment(){
        while(reader.canRead()){
            char ch = reader.read();
            if(!isAllowedCommentCharacter(ch)) throw new ParseException(reader.sliceChar(), "Illegal character '", reader.sliceChar(), "' (", (int)ch, ") in comment.");
        }
        return reader.slice();
    }

    protected Token parseValue(char boundary){
        while(reader.canRead()){
            char ch = reader.read();
            if(ch == boundary){
                return new ValueToken(reader.slice());
            } else if(isAllowedLiteralCharacter(ch)) {
                continue;
            } else {
                throw new ParseException(reader.sliceChar(), "Illegal character ", reader.sliceChar(), " (", (int)ch, ") in value.");
            }
        }
        throw new ParseException(line.getTrace().end(), "Missing closing character ", boundary, " for value.");
    }

    protected Token parseWord(){
        reader.back();

        boolean firstChar = true;

        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasUpperFirst = false;
        boolean hasUnderscore = false;

        while(reader.canRead()){
            char ch = reader.read();
            if(isLowerCharacter(ch)){
                hasLower = true;
                firstChar = false;
            } else if(isUpperCharacter(ch)){
                if(firstChar) hasUpperFirst = true;
                hasUpper = true;
                firstChar = false;
            } else if(isNumber(ch)){
                hasNumber = true;
            } else if(ch == '_'){
                hasUnderscore = true;
            } else {
                reader.back();
                break;
            }
        }

        return new WordToken(reader.slice(), hasLower, hasUpper, hasNumber, hasUpperFirst, hasUnderscore);
    }

    protected Token parseSpecial(){
        return new SpecialToken(reader.slice());
    }
}
