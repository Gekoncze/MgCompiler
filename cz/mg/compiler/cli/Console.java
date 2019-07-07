package cz.mg.compiler.cli;

import cz.mg.collections.text.ReadonlyText;


public interface Console {
    public void out(String s);

    public default void out(ReadonlyText s){
        out(s.toString());
    }

    public default void outln(String s){
        out(s + "\n");
    }

    public default void outln(ReadonlyText s){
        out(s.toString() + "\n");
    }

    public String in();
}
