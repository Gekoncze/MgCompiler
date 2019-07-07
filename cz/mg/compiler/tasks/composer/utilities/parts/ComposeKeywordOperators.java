package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.collections.list.chainlist.ChainListItem;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Keyword;
import cz.mg.compiler.entities.structured.parts.Operator;
import cz.mg.compiler.tasks.composer.utilities.OperatorUtilities;
import cz.mg.compiler.utilities.debug.Text;
import static cz.mg.compiler.tasks.composer.utilities.OperatorUtilities.OPS_K;


public class ComposeKeywordOperators implements ComposeParts {
    @Override
    public void compose(ChainList<Part> parts) {
        mainloop: for(int i = 0; i < parts.count(); i++){
            ChainListItem<Part> i0 = parts.getItem(i+0);
            if(i0.getData() instanceof Keyword){
                Text content = i0.getData().getContent();
                for(OperatorUtilities.Op op : OPS_K){
                    if(op.operator.equals(content.toString())){
                        i0.setData(new Operator(content));
                        continue mainloop;
                    }
                }
            }
        }
    }
}
