package cz.mg.compiler.entities.logical.mg.other;

import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.logical.mg.NamedLogicalMgEntity;
import cz.mg.compiler.entities.logical.mg.links.DatatypeDefinitionLink;
import cz.mg.compiler.utilities.debug.Text;


public class NativeVariable extends NamedLogicalMgEntity {
    @Part
    private final DatatypeDefinitionLink datatype;

    private final int referenceCount;

    public NativeVariable(Text name, DatatypeDefinitionLink datatype, int referenceCount) {
        super(name);
        this.datatype = datatype;
        this.referenceCount = referenceCount;
    }

    public DatatypeDefinitionLink getDatatype() {
        return datatype;
    }

    public int getReferenceCount() {
        return referenceCount;
    }
}
