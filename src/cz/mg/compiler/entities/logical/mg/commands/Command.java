package cz.mg.compiler.entities.logical.mg.commands;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.LogicalMgEntity;
import cz.mg.compiler.entities.logical.mg.calls.Call;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class Command extends LogicalMgEntity {
    @Part
    private Call call = null;

    public Command(Trace trace) {
        super(trace);
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }
}
