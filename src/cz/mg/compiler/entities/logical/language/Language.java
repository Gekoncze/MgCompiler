package cz.mg.compiler.entities.logical.language;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.ToplevelEntity;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.entities.structured.parts.chains.Path;
import cz.mg.compiler.tasks.builder.BuildException;
import cz.mg.compiler.utilities.debug.Text;


public class Language extends ToplevelEntity {
    @Part
    private final ChainList<Location> locations = new CachedChainList<>();

    public Language() {
    }

    public ChainList<Location> getLocations() {
        return locations;
    }

    public Location createLocation(String stringPath){
        Text textPath = new Text(stringPath, "");
        Array<Text> textPathParts = textPath.splitByEach(".");
        ChainList<cz.mg.compiler.entities.structured.Part> namePath = new ChainList<>();
        for(Text text : textPathParts) namePath.addLast(new Name(text));
        Path pathPart = new Path(namePath);
        return createLocation(pathPart);
    }

    public Location createLocation(Path path){
        Location currentLocation = null;
        for(cz.mg.compiler.entities.structured.Part part : path.getParts()){
            if(part instanceof Name){
                if(currentLocation == null){
                    currentLocation = createLocation(part.getContent());
                } else {
                    currentLocation = createLocation(currentLocation, part.getContent());
                }
            } else {
                throw new BuildException(part, "Expected \"Name\", but got \"", part.getClass().getSimpleName(), "\" (", part, ").");
            }
        }
        return currentLocation;
    }

    private Location createLocation(Text name){
        Location newLocation = findLocation(getLocations(), name);
        if(newLocation == null) getLocations().addLast(newLocation = new Location(name));
        return newLocation;
    }

    private Location createLocation(Location location, Text name){
        Location newLocation = findLocation(location.getEntities(), name);
        if(newLocation == null) location.getEntities().addLast(newLocation = new Location(name));
        return newLocation;
    }

    private final Location findLocation(ChainList entities, Text name){
        for(Object entity : entities){
            if(entity instanceof Location){
                if(((Location) entity).getName().equals(name)) return (Location)entity;
            }
        }
        return null;
    }
}
