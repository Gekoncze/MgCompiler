package cz.mg.compiler.cli.commands;

import cz.mg.collections.text.Text;
import cz.mg.compiler.Element;
import cz.mg.compiler.cli.*;


public class TreeCommand extends Command {
    public TreeCommand() {
        super("tree", "shows child elements of given element recursively");
    }

    @Override
    public void run(Explorer explorer, Arguments arguments) {
        Element element = findElement(explorer, arguments);
        printTree(explorer, element, 0);
    }

    private static void printTree(Explorer explorer, Element parent, int level){
        explorer.getConsole().out(Text.repeat("    ", level));
        explorer.getConsole().outln(Utilities.elementToString(parent));
        for(Element child : Utilities.getChildElements(parent)){
            printTree(explorer, child, level + 1);
        }
    }
}
