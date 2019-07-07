package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.collections.list.chainlist.ChainListItem;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Keyword;
import cz.mg.compiler.entities.structured.parts.Null;
import cz.mg.compiler.entities.structured.parts.Void;
import cz.mg.compiler.utilities.debug.Text;


public class ComposeKeywordValues implements ComposeParts {
    @Override
    public void compose(ChainList<Part> parts) {
        mainloop: for(int i = 0; i < parts.count(); i++){
            ChainListItem<Part> i0 = parts.getItem(i+0);
            if(i0.getData() instanceof Keyword){
                Text content = i0.getData().getContent();
                if(content.toString().equals("NULL")){
                    i0.setData(new Null(content));
                    continue mainloop;
                }
                if(content.toString().equals("VOID")){
                    i0.setData(new Void(content));
                    continue mainloop;
                }
            }
        }
    }
}
