package cz.mg.compiler.entities.logical.mg.links;

import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.LogicalMgEntity;
import cz.mg.compiler.utilities.debug.Trace;


public class Link<E extends LogicalMgEntity> extends LogicalMgEntity {
    private final Context context;
    private E link = null;

    public Link(Trace trace, Context context) {
        super(trace);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public E getLink() {
        return link;
    }

    public void setLink(E link) {
        this.link = link;
    }
}
