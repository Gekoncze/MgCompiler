package cz.mg.compiler.cli.commands;

import cz.mg.compiler.Element;
import cz.mg.compiler.cli.Arguments;
import cz.mg.compiler.cli.Command;
import cz.mg.compiler.cli.Explorer;
import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.entities.Entity;
import cz.mg.compiler.tasks.Task;
import java.io.PrintWriter;
import java.io.StringWriter;
import cz.mg.compiler.Compiler;


public class ErrorsCommand extends Command {
    public ErrorsCommand() {
        super("errors", "shows all errors in given element");
    }

    @Override
    public void run(Explorer explorer, Arguments arguments) {
        Element element = findElement(explorer, arguments);
        if(element instanceof Compiler) element = ((Compiler)element).getMainTask();
        if(element instanceof Entity) return;
        if(element instanceof Task) printErrorsTask(explorer, (Task) element);


    }

    private static void printErrorsTask(Explorer explorer, Task task){
        for(CompileException e : task.getAllErrors()) explorer.getConsole().outln(exceptionToString(e) + "\n");
    }

    public static String exceptionToString(CompileException e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return
                "ERROR IN FILE " + e.getTrace().getFilename() + " " +
                "ROWS " + e.getTrace().getRowBegin() + ":" + e.getTrace().getRowEnd() + " " +
                "COLUMNS " + e.getTrace().getColumnBegin() + ":" + e.getTrace().getColumnEnd() + "\n" +
                sw.toString() + "\n\n";
    }
}
