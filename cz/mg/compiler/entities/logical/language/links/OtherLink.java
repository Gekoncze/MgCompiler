package cz.mg.compiler.entities.logical.language.links;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.utilities.debug.Text;


public class OtherLink extends Link {
    private final Text name;

    public OtherLink(Context context, Text name) {
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
