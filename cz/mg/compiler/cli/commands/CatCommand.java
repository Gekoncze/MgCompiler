package cz.mg.compiler.cli.commands;

import cz.mg.collections.Collection;
import cz.mg.compiler.Element;
import cz.mg.compiler.cli.*;

import java.lang.reflect.Field;


public class CatCommand extends Command {
    public CatCommand() {
        super("cat", "shows content of given element field");
    }

    @Override
    public void run(Explorer explorer, Arguments arguments) {
        if(arguments.getSwitchy() == null) throw new IllegalArgumentException("Missing field name. Example: -strawberry");
        Element element = findElement(explorer, arguments);
        Collection<Field> fields = Utilities.getAllFields(element.getClass(), true);
        Field field = null;
        String fieldName = arguments.getSwitchy().replaceFirst("-", "");
        for(Field f : fields) if(f.getName().equals(fieldName)) field = f;
        if(field == null) explorer.getConsole().outln("UNKNOWN FIELD");
        else explorer.getConsole().outln(Utilities.readField(element, field) + "");
    }
}
