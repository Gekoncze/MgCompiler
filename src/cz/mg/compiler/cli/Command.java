package cz.mg.compiler.cli;

import cz.mg.compiler.Element;


public abstract class Command {
    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void run(Explorer explorer, Arguments arguments);

    public Element findElement(Explorer explorer, Arguments arguments){
        int id = arguments.getNumber() != null ? arguments.getNumber() : explorer.getCurrentElement().getId();
        return Utilities.findElement(id, explorer.getRootElement());
    }
}
