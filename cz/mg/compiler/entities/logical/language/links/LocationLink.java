package cz.mg.compiler.entities.logical.language.links;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.Location;
import cz.mg.compiler.utilities.debug.Text;


public class LocationLink extends NamedLink<Location> {
    public LocationLink(Context context, Text name) {
        super(context, name);
    }
}
