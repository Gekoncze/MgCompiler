package cz.mg.compiler.cli.commands;

import cz.mg.compiler.Element;
import cz.mg.compiler.cli.Arguments;
import cz.mg.compiler.cli.Command;
import cz.mg.compiler.cli.Explorer;


public class CdCommand extends Command {
    public CdCommand() {
        super("cd", "moves to given element");
    }

    @Override
    public void run(Explorer explorer, Arguments arguments) {
        Element element = findElement(explorer, arguments);
        explorer.setCurrentElement(element);
    }
}
