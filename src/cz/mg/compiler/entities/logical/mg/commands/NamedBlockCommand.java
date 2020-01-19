package cz.mg.compiler.entities.logical.mg.commands;

import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class NamedBlockCommand extends BlockCommand {
    private Text name = null;

    public NamedBlockCommand(Trace trace) {
        super(trace);
    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + name + "]";
    }
}
