package cz.mg.compiler.cli.commands;

import cz.mg.compiler.cli.Arguments;
import cz.mg.compiler.cli.Command;
import cz.mg.compiler.cli.Console;
import cz.mg.compiler.cli.Explorer;


public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "shows all commands");
    }

    @Override
    public void run(Explorer explorer, Arguments arguments) {
        explorer.getConsole().outln("Commands:");
        for(Command command : explorer.COMMANDS){
            explorer.getConsole().outln("    " + command.getName() + " - " + command.getDescription());
        }
    }
}
