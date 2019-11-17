package cz.mg.compiler.entities.logical.project;

import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.logical.LogicalEntity;


public class FilePath extends LogicalEntity {
    private final Text path;

    public FilePath(Text path) {
        super(path.getTrace());
        this.path = path;
    }

    public Text getPath() {
        return path;
    }
}
