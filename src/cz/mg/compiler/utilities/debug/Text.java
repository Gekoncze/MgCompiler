package cz.mg.compiler.utilities.debug;

import cz.mg.collections.array.Array;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.collections.text.SubText;


public class Text extends ReadonlyText implements Traceable {
    private final SubText subtext;
    private final String filename;

    public Text(String text, String filename){
        this.subtext = new SubText(text);
        this.filename = filename;
    }

    private Text(SubText text, String filename){
        this.subtext = text;
        this.filename = filename;
    }
    
    @Override
    public Trace getTrace() {
        return new Trace(filename, subtext.getReferencedString(), subtext.getBegin(), subtext.getEnd());
    }

    @Override
    public int count(){
        return subtext.count();
    }

    @Override
    public Character get(int i) {
        return subtext.get(i);
    }

    public Text slice(Integer begin, Integer end) {
        return convert(subtext.slice(begin, end));
    }
    
    public Text trim() {
        return convert(subtext.trim());
    }
    
    public Array<Text> splitByEach(Character delim) {
        return convert(subtext.splitByEach(delim));
    }

    public Array<Text> splitByEach(String delims) {
        return convert(subtext.splitByEach(delims));
    }
    
    public Array<Text> splitByEachNoBlank(Character delim) {
        return convert(subtext.splitByEachNoBlank(delim));
    }

    public Array<Text> splitByEachNoBlank(String delims) {
        return convert(subtext.splitByEachNoBlank(delims));
    }

    public Array<Text> splitByWhole(Character delim) {
        return convert(subtext.splitByWhole(delim));
    }

    public Array<Text> splitByWhole(String delim) {
        return convert(subtext.splitByWhole(delim));
    }

    public Array<Text> splitByWholeNoBlank(Character delim) {
        return convert(subtext.splitByWholeNoBlank(delim));
    }

    public Array<Text> splitByWholeNoBlank(String delim) {
        return convert(subtext.splitByWholeNoBlank(delim));
    }

    private Text convert(SubText subText){
        return new Text(subText, filename);
    }

    private Array<Text> convert(Array<SubText> subTexts){
        Array<Text> texts = new Array<>(subTexts.count());
        for(int i = 0; i < subTexts.count(); i++) texts.set(i, new Text(subTexts.get(i), filename));
        return texts;
    }

    @Override
    public int count(Character ch){
        return subtext.count(ch);
    }

    @Override
    public String toString() {
        return subtext.toString();
    }

    public static Text merge(Text... texts){
        Text result = null;
        for(Text t : texts) result = mergeTwo(result, t);
        return result;
    }

    private static Text mergeTwo(Text a, Text b){
        if(a == null && b == null) return null;
        if(a == null) return b;
        if(b == null) return a;
        if(a == b) return a;

        if(a.subtext.getReferencedString() == b.subtext.getReferencedString()){
            if(a.filename == b.filename){
                String s = a.subtext.getReferencedString();
                String filename = a.filename;
                int begin = Math.min(a.subtext.getBegin(), b.subtext.getBegin());
                int end = Math.max(a.subtext.getEnd(), b.subtext.getEnd());
                return new Text(new SubText(s, begin, end), filename);
            }
            throw new IllegalArgumentException("Merging strings from different files.");
        }
        throw new IllegalArgumentException("Merging strings with different string reference.");
    }

    public static Text between(Text a, Text b){
        if(a == null || b == null) return null;
        if(a.subtext.getBegin() > b.subtext.getBegin()) return between(b, a);

        if(a.subtext.getReferencedString() == b.subtext.getReferencedString()){
            if(a.filename == b.filename){
                String s = a.subtext.getReferencedString();
                String filename = a.filename;

                int delta = b.subtext.getBegin() - a.subtext.getEnd();
                if(delta <= 0) return null;

                Integer begin = a.subtext.getEnd();
                Integer end = b.subtext.getBegin();
                return new Text(new SubText(s, begin, end), filename);
            }
            throw new IllegalArgumentException("Merging strings from different files.");
        }
        throw new IllegalArgumentException("Merging strings with different string reference.");
    }
}
