package cz.mg.compiler.cli.commands;

import cz.mg.compiler.cli.Arguments;
import cz.mg.compiler.cli.Command;
import cz.mg.compiler.cli.Explorer;


public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "exits the application");
    }

    @Override
    public void run(Explorer explorer, Arguments arguments) {
        System.exit(0);
    }
}
