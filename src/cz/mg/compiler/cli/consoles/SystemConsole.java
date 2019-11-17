package cz.mg.compiler.cli.consoles;

import cz.mg.compiler.cli.Console;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SystemConsole implements Console {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void out(String s){
        System.out.print(s);
    }

    @Override
    public String in(){
        out(">>> ");
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
