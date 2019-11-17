package cz.mg.compiler.tasks.composer.utilities.parts;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.collections.list.chainlist.ChainListItem;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.entities.structured.parts.Expression;
import cz.mg.compiler.entities.structured.parts.Operator;
import static cz.mg.compiler.tasks.composer.utilities.OperatorUtilities.OPS;
import static cz.mg.compiler.tasks.composer.utilities.OperatorUtilities.Op;


public class ComposeOperatorExpressions implements ComposeParts {
    public ComposeOperatorExpressions() {
    }

    @Override
    public void compose(ChainList<Part> parts) {
        for(int p = 0; p < OPS.length; p++){
            for(ChainListItem<Part> middle = parts.getFirstItem(); middle != null; middle = middle.getNextItem()){
                ChainListItem<Part> left = middle.getPreviousItem();
                ChainListItem<Part> right = middle.getNextItem();
                if(middle.getData() instanceof Operator){
                    for(Op op : OPS[p]){
                        if(middle.getData().getContent().equals(op.operator)){
                            if(op.operator.equals("+") || op.operator.equals("-")){
                                switch (op.position){
                                    case LEFT: if(left == null && right != null) composeLeft(null, middle, right); break;
                                    case MIDDLE: if(left != null && right != null) composeMiddle(left, middle, right); break;
                                    case RIGHT: if(left != null && right == null) composeRight(left, middle, null); break;
                                    default: throw new RuntimeException();
                                }
                            } else {
                                switch (op.position){
                                    case LEFT: if(right != null) composeLeft(null, middle, right); break;
                                    case MIDDLE: if(left != null && right != null) composeMiddle(left, middle, right); break;
                                    case RIGHT: if(left != null) composeRight(left, middle, null); break;
                                    default: throw new RuntimeException();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void composeLeft(ChainListItem<Part> left, ChainListItem<Part> middle, ChainListItem<Part> right){
        middle.setData(new Expression(middle.getData(), right.remove()));
    }

    private static void composeMiddle(ChainListItem<Part> left, ChainListItem<Part> middle, ChainListItem<Part> right){
        middle.setData(new Expression(middle.getData(), left.remove(), right.remove()));
    }

    private static void composeRight(ChainListItem<Part> left, ChainListItem<Part> middle, ChainListItem<Part> right){
        middle.setData(new Expression(middle.getData(), left.remove()));
    }
}
