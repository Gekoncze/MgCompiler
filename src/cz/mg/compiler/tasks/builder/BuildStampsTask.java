package cz.mg.compiler.tasks.builder;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.Stamps;
import cz.mg.compiler.entities.logical.mg.calls.ValueCall;
import cz.mg.compiler.entities.logical.mg.links.StampLink;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Stamp;
import cz.mg.compiler.entities.structured.parts.Value;
import cz.mg.compiler.entities.structured.parts.chains.List;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.*;


public class BuildStampsTask extends BuildTask {
    @Link
    private final Block block;

    @Link
    private final Context context;

    @Link
    private Stamps stamps = null;

    public BuildStampsTask(Block block, Context context) {
        this.block = block;
        this.context = context;
    }

    public Stamps getStamps() {
        return stamps;
    }

    @Override
    protected void onRun() {
        if(block.getStamps() == null) return;
        if(block.getStamps().count() <= 0) return;
        stamps = new Stamps(stampsTrace(block.getStamps()));
        for(Stamp stamp : block.getStamps()){
            stamps.getStamps().addLast(new StampLink(
                    context,
                    stamp.getContent(),
                    buildArguments(stamp.getArguments())
            ));
        }
    }

    private static Trace stampsTrace(ChainList<Stamp> stamps){
        Text result = null;
        for(Stamp stamp : stamps) result = Text.merge(result, stamp.getContent());
        return result.getTrace();
    }

    private ChainList<ValueCall> buildArguments(List list){
        if(list == null) return null;
        return buildValueCalls(list.getParts());
    }

    private ChainList<ValueCall> buildValueCalls(ChainList<Part> parts){
        ChainList<ValueCall> valueCalls = new CachedChainList<>();
        for(Part part : parts) valueCalls.addLast(buildValueCall(part));
        return valueCalls;
    }

    private ValueCall buildValueCall(Part part){
        Value value = cast(part, Value.class);
        return new ValueCall(value.getTrace(), value.getValue());
    }
}
