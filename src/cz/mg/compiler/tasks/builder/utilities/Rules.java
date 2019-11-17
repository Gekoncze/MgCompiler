package cz.mg.compiler.tasks.builder.utilities;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.entities.structured.Part;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.BuildException;
import cz.mg.compiler.utilities.debug.PlaceholderText;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;


public class Rules {
    private final Rule[] rules;

    public Rules(Rule... rules) {
        this.rules = rules;
    }

    public void match(Task parentTask, ChainList<Block> childBlocks){
        for(Block child : childBlocks){
            match(parentTask, child);
        }
    }

    private void match(Task parentTask, Block child) {
        for (Rule rule : rules) {
            if (rule.match(parentTask, child)) return;
        }
        noRuleFoundError(child);
    }

    private void noRuleFoundError(Block child){
        ChainList message = new CachedChainList();
        message.addLast("Could not recognise block.\n");
        message.addLast("    Expected patterns:\n");
        if(rules != null && rules.length > 0){
            for(Rule rule : rules){
                message.addLast("        ");
                message.addLast(rule.getPattern());
                message.addLast("\n");
            }
        } else {
            message.addLast("        <none>\n");
        }
        message.addLast("    Given pattern:\n");
        message.addLast("        ");
        Pattern givenPattern = Pattern.create(child);
        for(int i = 0; i < givenPattern.getFilters().length; i++){
            message.addLast("[");
            message.addLast(new PlaceholderText(child.getParts().get(i), givenPattern.getFilters()[i].toString()));
            message.addLast("]");
        }
        message.addLast("\n");
        throw new BuildException(effectiveBlockTrace(child), (Object[])new Array<>(message).getArray());
    }

    public static Trace effectiveBlockTrace(Block block){
        Text content = null;
        for(Part part : block.getParts()) content = Text.merge(content, part.getContent());
        return content.getTrace();
    }
}
