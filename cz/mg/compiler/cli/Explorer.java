package cz.mg.compiler.cli;

import cz.mg.compiler.Element;
import cz.mg.compiler.cli.commands.*;


public class Explorer {
    public static final Command[] COMMANDS = new Command[]{
            new HelpCommand(),
            new LsCommand(),
            new PwdCommand(),
            new TreeCommand(),
            new CdCommand(),
            new LsfieldsCommand(),
            new CatCommand(),
            new ErrorsCommand(),
            new CommandErrorCommand(),
            new ExitCommand(),
    };

    private final Console console;
    private final Element rootElement;
    private Element currentElement;
    private Exception lastCommandError = null;

    public Explorer(Console console, Element rootElement) {
        this.console = console;
        this.currentElement = rootElement;
        this.rootElement = rootElement;
    }

    public Console getConsole() {
        return console;
    }

    public Element getRootElement(){
        return rootElement;
    }

    public Element getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(Element currentElement) {
        this.currentElement = currentElement;
    }

    public Exception getLastCommandError() {
        return lastCommandError;
    }

    private void printTitle(){
        getConsole().outln("############################");
        getConsole().outln("### COMPILATION EXPLORER ###");
        getConsole().outln("############################");
        getConsole().outln("");
    }

    public void run(){
        printTitle();
        run("help");
        while(true) run(getConsole().in());
    }

    private void run(String input){
        try {
            Arguments arguments = Arguments.create(input);
            if(arguments.getName() != null){
                boolean found = false;
                for(Command command : COMMANDS){
                    if(command.getName().equals(arguments.getName())){
                        command.run(this, arguments);
                        found = true;
                        break;
                    }
                }
                if(!found) getConsole().outln("UNKNOWN COMMAND");
            }
        } catch (Exception e){
            getConsole().outln("COMMAND ERROR: " + e.getClass().getSimpleName() + ": " + e.getMessage());
            lastCommandError = e;
        }
    }
}
