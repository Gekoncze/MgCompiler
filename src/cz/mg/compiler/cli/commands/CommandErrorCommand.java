package cz.mg.compiler.cli.commands;

import cz.mg.compiler.cli.Arguments;
import cz.mg.compiler.cli.Command;
import cz.mg.compiler.cli.Explorer;
import java.io.PrintWriter;
import java.io.StringWriter;


public class CommandErrorCommand extends Command {
    public CommandErrorCommand() {
        super("commanderror", "shows details of last command error");
    }

    @Override
    public void run(Explorer explorer, Arguments arguments) {
        Exception e = explorer.getLastCommandError();
        if(e != null) explorer.getConsole().outln(exceptionToString(e));
        else explorer.getConsole().outln("NO ERROR");
    }

    public static String exceptionToString(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return e.getClass().getSimpleName() + ": " + e.getMessage() + "\n" + sw.toString();
    }
}
