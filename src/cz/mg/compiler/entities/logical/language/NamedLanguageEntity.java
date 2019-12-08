package cz.mg.compiler.entities.logical.language;

import cz.mg.compiler.Named;
import cz.mg.compiler.utilities.debug.Text;


public abstract class NamedLanguageEntity extends LanguageEntity implements Named {
    private final Text name;

    public NamedLanguageEntity(Text name) {
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
