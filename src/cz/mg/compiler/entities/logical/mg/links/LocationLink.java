package cz.mg.compiler.entities.logical.mg.links;

import cz.mg.compiler.entities.logical.mg.Context;
import cz.mg.compiler.entities.logical.mg.Location;
import cz.mg.compiler.utilities.debug.Text;


public class LocationLink extends NamedLink<Location> {
    public LocationLink(Context context, Text name) {
        super(context, name);
    }
}
