package cz.mg.compiler.entities.logical.language.links;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.NamedLanguageEntity;
import cz.mg.compiler.utilities.debug.Text;


public class NamedLink<E extends NamedLanguageEntity> extends Link<E> {
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
