package cz.mg.compiler.entities.logical.language.other;

import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.entities.logical.language.NamedLanguageEntity;
import cz.mg.compiler.entities.logical.language.links.DatatypeDefinitionLink;
import cz.mg.compiler.utilities.debug.Text;


public class NativeVariable extends NamedLanguageEntity {
    @Child
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
