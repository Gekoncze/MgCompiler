package cz.mg.compiler.entities.logical.mg.other;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.calls.Call;
import cz.mg.compiler.utilities.debug.Text;


public class Property extends Variable {
    @Part
    private final Call call;

    public Property(Text name, Datatype datatype, Call call) {
        super(name, datatype);
        this.call = call;
    }

    public Call getCall() {
        return call;
    }
}
