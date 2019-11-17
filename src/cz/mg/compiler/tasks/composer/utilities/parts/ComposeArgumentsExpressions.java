package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.collections.list.chainlist.ChainListItem;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Colon;
import cz.mg.compiler.entities.structured.parts.Expression;
import cz.mg.compiler.entities.structured.parts.chains.List;
import cz.mg.compiler.tasks.composer.ComposeException;
import static cz.mg.compiler.tasks.composer.utilities.PartUtilities.*;


public class ComposeArgumentsExpressions implements ComposeParts {
    @Override
    public void compose(ChainList<Part> parts) {
        for(ChainListItem<Part> middle = parts.getFirstItem(); middle != null; middle = middle.getNextItem()){
            ChainListItem<Part> left = middle.getPreviousItem();
            if(middle.getData() instanceof Colon){
                groupCall(left, middle);
            }
        }
    }

    private static void groupCall(ChainListItem<Part> left, ChainListItem<Part> middle){
        if(left == null) throw new ComposeException(middle.getData(), "Missing part on left.");
        Colon colon = (Colon) middle.getData();
        Part part = sweep(colon);
        List list = toList(part);
        middle.setData(new Expression(left.remove(), list.getParts()));
    }
}
