package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Keyword;
import cz.mg.compiler.entities.structured.parts.OtherGroup;
import cz.mg.compiler.entities.structured.parts.groups.Group;


public class ComposeKeywordGroups implements ComposeParts {
    public ComposeKeywordGroups() {
    }

    @Override
    public void compose(ChainList<Part> parts) {
        ChainList<Part> newParts = new CachedChainList<>();
        ChainList<Part> groupParts = new CachedChainList<>();

        for(Part part : parts){
            if(part instanceof Keyword){
                if(groupParts.count() != 0) newParts.addLast(new OtherGroup(groupParts));
                newParts.addLast(part);
                if(groupParts.count() != 0) groupParts = new CachedChainList<>();
            } else {
                groupParts.addLast(part);
            }
        }
        if(groupParts.count() != 0) newParts.addLast(new OtherGroup(groupParts));

        if(newParts.count() != 1){
            parts.clear();
            parts.addCollectionLast(newParts);
        }
    }
}
