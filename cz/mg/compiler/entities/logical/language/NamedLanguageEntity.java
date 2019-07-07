package cz.mg.compiler.entities.logical.language;

import cz.mg.compiler.utilities.debug.Text;


public abstract class NamedLanguageEntity extends LanguageEntity {
    private final Text name;

    public NamedLanguageEntity(Text name) {
        super(name.getTrace());
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
