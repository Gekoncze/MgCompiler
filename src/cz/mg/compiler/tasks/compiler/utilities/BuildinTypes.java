package cz.mg.compiler.tasks.compiler.utilities;

import cz.mg.collections.array.Array;
import cz.mg.compiler.entities.logical.mg.LogicalMg;
import cz.mg.compiler.entities.logical.mg.Location;
import cz.mg.compiler.entities.logical.mg.definitions.TypeDefinition;
import cz.mg.compiler.utilities.debug.Text;


public class BuildinTypes {
    public static final TypeDefinition POINTER = new TypeDefinition(new Text("Pointer", ""));
    public static final TypeDefinition ANY = new TypeDefinition(new Text("Any", ""));

    public static void addBuildinTypes(LogicalMg logicalMg){
        Location location = logicalMg.createLocation("cz.mg.types");
        location.getEntities().addCollectionLast(new Array<>(
                POINTER,
                ANY
        ));
    }
}
