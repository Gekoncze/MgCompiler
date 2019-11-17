package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.collections.list.chainlist.ChainListItem;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Name;
import cz.mg.compiler.entities.structured.parts.Declaration;
import cz.mg.compiler.entities.structured.parts.Type;


public class ComposeDeclarations implements ComposeParts {
    public ComposeDeclarations() {
    }

    @Override
    public void compose(ChainList<Part> parts) {
        for(int i = 0; i < parts.count(); i++){
            ChainListItem<Part> i0 = parts.getItem(i+0);
            ChainListItem<Part> i1 = parts.getItem(i+1);
            if(i0 != null && i1 != null){
                Part p0 = i0.getData();
                Part p1 = i1.getData();
                if(p0 instanceof Type && p1 instanceof Name){
                    i0.setData(new Declaration((Type)p0, (Name)p1));
                    i1.remove();
                }
            }
        }
    }
}
