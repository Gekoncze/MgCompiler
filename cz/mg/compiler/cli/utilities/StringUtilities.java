package cz.mg.compiler.cli.utilities;

import cz.mg.compiler.Element;
import cz.mg.compiler.tasks.Task;


public class StringUtilities extends ReflectionUtilities {
    public static String elementToString(Element element){
        String errors = element instanceof Task ? " " + taskErrorsToString((Task) element) : "";
        return element.toString() + errors;
    }

    public static String taskErrorsToString(Task task){
        if(task.getAllErrors().count() <= 0) return "";

        boolean indirectErrors = task.getAllErrors().count() != 0;
        boolean directErrors = task.getErrors().count() != 0;
        String mark = "";
        if(indirectErrors) mark = "*";
        if(directErrors) mark = "!";

        return "[" + task.getAllErrors().count() + mark + "]";
    }
}
