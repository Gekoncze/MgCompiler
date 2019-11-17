package cz.mg.compiler.tasks.compiler.utilities;

import cz.mg.collections.array.Array;
import cz.mg.compiler.entities.logical.language.Language;
import cz.mg.compiler.entities.logical.language.Location;
import cz.mg.compiler.entities.logical.language.definitions.StampDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class BuildinStamps {
    public static final StampDefinition INVISIBLE = new StampDefinition(new Text("invisible", ""));
    public static final StampDefinition PUBLIC = new StampDefinition(new Text("public", ""));
    public static final StampDefinition PRIVATE = new StampDefinition(new Text("private", ""));
    public static final StampDefinition PROTECTED = new StampDefinition(new Text("protected", ""));
    public static final StampDefinition INTERNAL = new StampDefinition(new Text("internal", ""));
    public static final StampDefinition EXTERNAL = new StampDefinition(new Text("external", ""));
    public static final StampDefinition LOCAL = new StampDefinition(new Text("local", ""));

    public static final StampDefinition READWRITE = new StampDefinition(new Text("readwrite", ""));
    public static final StampDefinition READONLY = new StampDefinition(new Text("readonly", ""));
    public static final StampDefinition WRITEONLY = new StampDefinition(new Text("writeonly", ""));
    public static final StampDefinition CONSTANT = new StampDefinition(new Text("constant", ""));

    public static final StampDefinition MODIFY = new StampDefinition(new Text("modify", ""));
    public static final StampDefinition PRIVATEINHERITANCE = new StampDefinition(new Text("privateinheritance", ""));
    public static final StampDefinition PUBLICENTITIES = new StampDefinition(new Text("privateinheritance", ""));

    public static void addBuildinStamps (Language language){
        Location location = language.createLocation("cz.mg.stamps");
        location.getEntities().addCollectionLast(new Array<>(
                INVISIBLE,
                PUBLIC,
                PRIVATE,
                PROTECTED,
                INTERNAL,
                EXTERNAL,
                LOCAL,

                READWRITE,
                READONLY,
                WRITEONLY,
                CONSTANT,

                MODIFY,
                PRIVATEINHERITANCE
        ));
    }
}
