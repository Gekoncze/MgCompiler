package cz.mg.compiler.cli.commands;

import cz.mg.compiler.Element;
import cz.mg.compiler.cli.*;


public class LsCommand extends Command {
    public LsCommand() {
        super("ls", "shows child elements of given element");
    }

    @Override
    public void run(Explorer explorer, Arguments arguments) {
        Element parent = findElement(explorer, arguments);
        for(Element child : Utilities.getChildElements(parent)){
            explorer.getConsole().outln(Utilities.elementToString(child));
        }
    }
}
