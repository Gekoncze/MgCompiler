package cz.mg.compiler.entities.logical.mg.links;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.calls.ValueCall;
import cz.mg.compiler.entities.logical.mg.definitions.StampDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class StampLink extends NamedLink<StampDefinition> {
    private final ChainList<ValueCall> valueCalls;

    public StampLink(Context context, Text name, ChainList<ValueCall> valueCalls) {
        super(context, name);
        this.valueCalls = valueCalls;
    }

    public ChainList<ValueCall> getValueCalls() {
        return valueCalls;
    }
}
