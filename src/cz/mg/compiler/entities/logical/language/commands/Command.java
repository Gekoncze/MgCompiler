package cz.mg.compiler.entities.logical.language.commands;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.language.LanguageEntity;
import cz.mg.compiler.entities.logical.language.calls.Call;
import cz.mg.compiler.utilities.debug.Trace;


public abstract class Command extends LanguageEntity {
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
