package cz.mg.compiler.entities.input;

import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.Entity;


public abstract class InputEntity extends Entity {
    public abstract void load();
    public abstract Text getText();
}
