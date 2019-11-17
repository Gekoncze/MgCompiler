package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.collections.list.chainlist.ChainListItem;
import cz.mg.compiler.tasks.composer.utilities.OperatorUtilities.Op;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Special;
import cz.mg.compiler.entities.structured.parts.Operator;
import static cz.mg.compiler.tasks.composer.utilities.OperatorUtilities.*;


public class ComposeOperators implements ComposeParts {
    public ComposeOperators() {
    }

    @Override
    public void compose(ChainList<Part> parts) {
        mainloop: for(int i = 0; i < parts.count(); i++){
            ChainListItem<Part> i0 = parts.getItem(i+0);
            ChainListItem<Part> i1 = parts.getItem(i+1);
            ChainListItem<Part> i2 = parts.getItem(i+2);
            boolean op0 = isSpecial(i0);
            boolean op1 = isSpecial(i1);
            boolean op2 = isSpecial(i2);
            if(op0 && op1 && op2){
                Text content = Text.merge(i0.getData().getContent(), i1.getData().getContent(), i2.getData().getContent());
                for(Op op : OPS_3){
                    if(op.operator.equals(content.toString())){
                        i0.setData(new Operator(content));
                        i1.remove();
                        i2.remove();
                        continue mainloop;
                    }
                }
            }
            if(op0 && op1){
                Text content = Text.merge(i0.getData().getContent(), i1.getData().getContent());
                for(Op op : OPS_2){
                    if(op.operator.equals(content.toString())){
                        i0.setData(new Operator(content));
                        i1.remove();
                        continue mainloop;
                    }
                }
            }
            if(op0){
                Text content = i0.getData().getContent();
                for(Op op : OPS_1){
                    if(op.operator.equals(content.toString())){
                        i0.setData(new Operator(content));
                        continue mainloop;
                    }
                }
            }
        }
    }

    private static boolean isSpecial(ChainListItem<Part> part){
        if(part == null) return false;
        return part.getData() instanceof Special;
    }
}
