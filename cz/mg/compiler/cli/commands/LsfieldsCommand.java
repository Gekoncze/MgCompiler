package cz.mg.compiler.cli.commands;

import cz.mg.collections.Collection;
import cz.mg.collections.text.Text;
import cz.mg.compiler.Element;
import cz.mg.compiler.cli.*;
import java.lang.reflect.Field;


public class LsfieldsCommand extends Command {
    public LsfieldsCommand() {
        super("lsfields", "shows given element fields");
    }

    @Override
    public void run(Explorer explorer, Arguments arguments) {
        Element element = findElement(explorer, arguments);
        Collection<Field> fields = Utilities.getAllFields(element.getClass(), true);
        for(Field field : fields){
            explorer.getConsole().outln(field.getName() + " [" + getShortContent(Utilities.readField(element, field) + "") + "]");
        }
    }

    private static String getShortContent(String content){
        Text text = new Text(content);
        if(text.count() > 100) text = text.slice(0, 100).append("...");
        return text.replace("\n", " ").toString();
    }
}
