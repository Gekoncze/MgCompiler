package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.OtherGroup;
import cz.mg.compiler.entities.structured.parts.Special;
import cz.mg.compiler.entities.structured.parts.chains.List;
import cz.mg.compiler.entities.structured.parts.groups.Group;


public class ComposeLists implements ComposeParts {
    public ComposeLists() {
    }

    @Override
    public void compose(ChainList<Part> parts) {
        if(containsList(parts)){
            ChainList<Part> listGroups = new CachedChainList<>();
            composeListGroups(listGroups, parts);

            List list = new List(listGroups);
            parts.clear();
            parts.addLast(list);
        }
    }

    private static void composeListGroups(ChainList<Part> listGroups, ChainList<Part> listParts){
        ChainList<Part> listGroupParts = new CachedChainList<>();
        for(Part part : listParts){
            if(isComma(part)){
                listGroups.addLast(new OtherGroup(listGroupParts));
                listGroupParts = new CachedChainList<>();
            } else {
                listGroupParts.addLast(part);
            }
        }
        listGroups.addLast(new OtherGroup(listGroupParts));
    }

    private static boolean containsList(ChainList<Part> parts){
        for(Part part : parts){
            if(isComma(part)){
                return true;
            }
        }
        return false;
    }

    private static boolean isComma(Part item){
        if(item instanceof Special){
            if(item.getContent().equals(",")) {
                return true;
            }
        }
        return false;
    }
}
