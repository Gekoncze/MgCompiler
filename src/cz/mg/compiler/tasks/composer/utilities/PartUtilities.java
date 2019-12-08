package cz.mg.compiler.tasks.composer.utilities;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainListItem;
import cz.mg.collections.text.Text;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.groups.UnitedGroup;
import cz.mg.compiler.entities.structured.parts.chains.List;
import cz.mg.compiler.entities.structured.parts.chains.Path;
import cz.mg.utilities.ReflectionUtilities;
import cz.mg.compiler.tasks.composer.ComposeException;


public class PartUtilities {
    public static Path toPath(Part part){
        if(part instanceof Path) return (Path) part;
        return new Path(new CachedChainList<>(part));
    }

    public static List toList(Part part){
        if(part instanceof List) return (List) part;
        return new List(new CachedChainList<>(part));
    }

    public static <T> T cast(Part part, Class c){
        if(ReflectionUtilities.objectof(part, c)) return (T) part;
        throw new ComposeException(part, "Expected \"", c.getSimpleName(), "\" but got \"", part.getClass().getSimpleName(), "\" (", part, ").");
    }

    public static Class check(Part part, Class... cs){
        for(Class c : cs) if(ReflectionUtilities.objectof(part, c)) return c;
        String options = "";
        for(Class c : cs) options = options + "\"" + c.getSimpleName() + "\" or ";
        options = new Text(options).replaceEnd(" or ", "").toString();
        throw new ComposeException(part, "Expected ", options, " but got \"", part.getClass().getSimpleName(), "\" (", part, ").");
    }

    public static Part sweep(Part part){
        if(part instanceof UnitedGroup){
            UnitedGroup group = (UnitedGroup) part;
            if(group.getParts().count() == 0) throw new ComposeException(part, "Empty group.");
            if(group.getParts().count() > 1) throw new ComposeException(group.getParts().get(1), "Forever alone part was not composed to any expression. Found ", group.getParts().count(), " parts, expected ", 1, ".");
            return group.getParts().getFirst();
        } else {
            return part;
        }
    }

    public static List sweepList(List list){
        for(ChainListItem<Part> item = list.getParts().getFirstItem(); item != null; item = item.getNextItem()){
            item.setData(sweep(item.getData()));
        }
        return list;
    }
}
