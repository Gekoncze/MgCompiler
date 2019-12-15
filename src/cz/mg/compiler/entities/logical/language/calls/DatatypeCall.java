package cz.mg.compiler.entities.logical.language.calls;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.language.other.Datatype;


public class DatatypeCall extends Call {
    @Part
    private final Datatype datatype;

    public DatatypeCall(Datatype datatype) {
        super(datatype.getTrace());
        this.datatype = datatype;
    }

    public Datatype getDatatype() {
        return datatype;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + datatype.getDatatypeDefinition().getName() + "]";
    }
}
