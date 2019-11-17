package cz.mg.compiler.entities.structured;

import cz.mg.compiler.utilities.debug.Text;


public class StructuredPage extends Block {
    public StructuredPage(Text content) {
        super(content, null, null, null);
    }

    @Override
    protected String toStringDetails() {
        return getTrace().getFilename();
    }
}
