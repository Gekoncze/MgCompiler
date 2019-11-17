package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.collections.list.chainlist.ChainListItem;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.entities.structured.parts.Special;
import cz.mg.compiler.entities.structured.parts.Type;
import cz.mg.compiler.utilities.debug.Text;


public class ComposeTypes implements ComposeParts {
    public ComposeTypes() {
    }

    @Override
    public void compose(ChainList<Part> parts) {
        for(int i = 0; i < parts.count(); i++){
            ChainListItem<Part> i0 = parts.getItem(i);
            ChainListItem<Part> i1 = parts.getItem(i+1);
            if(isName(i0)){
                if(isReferenceSpecial(i1) || isValueSpecial(i1)){
                    Name name = (Name) i0.getData();
                    Special special = mergeSpecials(i0);
                    i0.setData(new Type(name, special));
                }
            }
        }
    }

    private static Special mergeSpecials(ChainListItem<Part> item){
        int count = 0;
        Text content = null;
        while(isReferenceSpecial(item.getNextItem()) || isValueSpecial(item.getNextItem())){
            Text newContent = Text.merge(content, item.getNext().getContent());
            if(newContent.count() == (count + 1)){
                content = newContent;
                item.removeNext();
                count++;
            } else {
                break;
            }
        }
        if(content == null) return null;
        return new Special(content);
    }

    private static boolean isReferenceSpecial(ChainListItem<Part> part){
        if(part == null) return false;
        if(part.getData() instanceof Special) if(part.getData().getContent().equals("&")) return true;
        return false;
    }

    private static boolean isValueSpecial(ChainListItem<Part> part){
        if(part == null) return false;
        if(part.getData() instanceof Special) if(part.getData().getContent().equals("$")) return true;
        return false;
    }

    private static boolean isName(ChainListItem<Part> part){
        if(part == null) return false;
        return part.getData() instanceof Name;
    }
}
