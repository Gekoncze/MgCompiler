package cz.mg.compiler.cli.commands;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Element;
import cz.mg.compiler.cli.*;


public class PwdCommand extends Command {
    public PwdCommand() {
        super("pwd", "shows path from root element to given element");
    }

    @Override
    public void run(Explorer explorer, Arguments arguments) {
        Element element = findElement(explorer, arguments);
        ChainList<String> path = new ChainList<>();
        while(element != null){
            path.addFirst(element.getId() + "-" + element.getClass().getSimpleName());
            element = Utilities.findParentElement(element.getId(), explorer.getRootElement());
        }
        explorer.getConsole().outln("/" + path.toString("/"));
    }
}
