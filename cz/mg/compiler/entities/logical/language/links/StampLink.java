package cz.mg.compiler.entities.logical.language.links;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.calls.ValueCall;
import cz.mg.compiler.entities.logical.language.definitions.StampDefinition;
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
