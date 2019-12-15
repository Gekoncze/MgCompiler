package cz.mg.compiler.entities.logical.language.other;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.language.NamedLanguageEntity;
import cz.mg.compiler.utilities.debug.Text;


public class Variable extends NamedLanguageEntity {
    @Part
    private final Datatype datatype;

    public Variable(Text name, Datatype datatype) {
        super(name);
        this.datatype = datatype;
    }

    public Datatype getDatatype() {
        return datatype;
    }
}
