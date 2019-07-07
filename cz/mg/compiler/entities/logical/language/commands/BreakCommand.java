package cz.mg.compiler.entities.logical.language.commands;

import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class BreakCommand extends Command {
    private final Text targetName;

    public BreakCommand(Trace trace, Text targetName) {
        super(trace);
        this.targetName = targetName;
    }

    public Text getTargetName() {
        return targetName;
    }
}
