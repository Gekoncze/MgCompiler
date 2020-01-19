package cz.mg.compiler.entities.logical.mg;

import cz.mg.compiler.Named;
import cz.mg.compiler.utilities.debug.Text;


public abstract class NamedLogicalMgEntity extends LogicalMgEntity implements Named {
    private final Text name;

    public NamedLogicalMgEntity(Text name) {
        super(name.getTrace());
        this.name = name;
    }

    @Override
    public Text getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + name + "]";
    }
}
