package cz.mg.compiler.entities.logical.mg.links;

import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.NamedLogicalMgEntity;
import cz.mg.compiler.utilities.debug.Text;


public class NamedLink<E extends NamedLogicalMgEntity> extends Link<E> {
    private final Text name;

    public NamedLink(Context context, Text name) {
        super(name.getTrace(), context);
        this.name = name;
    }

    public Text getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + name + "]";
    }
}
