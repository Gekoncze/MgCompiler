package cz.mg.compiler.cli;

import java.util.StringTokenizer;


public class Arguments {
    private final String name;
    private final String switchy;
    private final Integer number;

    private Arguments(String name) {
        this.name = name;
        this.switchy = null;
        this.number = null;
    }

    private Arguments(String name, String switchy) {
        this.name = name;
        this.switchy = switchy;
        this.number = null;
    }

    private Arguments(String name, Integer number) {
        this.name = name;
        this.switchy = null;
        this.number = number;
    }

    private Arguments(String name, String switchy, Integer number) {
        this.name = name;
        this.switchy = switchy;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getSwitchy() {
        return switchy;
    }

    public Integer getNumber() {
        return number;
    }

    public static Arguments create(String input){
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        String[] arguments = new String[tokenizer.countTokens()];
        for(int i = 0; i < arguments.length; i++) arguments[i] = tokenizer.nextToken();
        return create(arguments);
    }

    private static Arguments create(String[] arguments){
        if(arguments.length == 0){
            return new Arguments(null);
        }

        if(arguments.length == 1){
            return new Arguments(arguments[0]);
        }

        if(arguments.length == 2){
            if(isSwitch(arguments[1])) return new Arguments(arguments[0], arguments[1]);
            if(isNumber(arguments[1])) return new Arguments(arguments[0], Integer.parseInt(arguments[1]));
            throw new IllegalArgumentException("Illegal second argument: " + arguments[1]);
        }

        if(arguments.length == 3){
            if(isSwitch(arguments[1]) && isNumber(arguments[2])) return new Arguments(arguments[0], arguments[1], Integer.parseInt(arguments[2]));
            if(isNumber(arguments[1]) && isSwitch(arguments[2])) return new Arguments(arguments[0], arguments[2], Integer.parseInt(arguments[1]));
            throw new IllegalArgumentException("Illegal second or third argument: " + arguments[1] + ", " + arguments[2]);
        }

        throw new IllegalArgumentException("Too many arguments: " + arguments.length);
    }

    private static boolean isSwitch(String s){
        try {
            return s.charAt(0) == '-';
        } catch (Exception e){
            return false;
        }
    }

    private static boolean isNumber(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
