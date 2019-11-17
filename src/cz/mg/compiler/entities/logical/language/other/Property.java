package cz.mg.compiler.entities.logical.language.other;

import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.logical.language.calls.Call;
import cz.mg.compiler.utilities.debug.Text;


public class Property extends Variable {
    @Child
    private final Call call;

    public Property(Text name, Datatype datatype, Call call) {
        super(name, datatype);
        this.call = call;
    }

    public Call getCall() {
        return call;
    }
}
