package cz.mg.compiler.tasks.builder.utilities;

import cz.mg.compiler.entities.structured.Block;
import cz.mg.compiler.tasks.Task;


public class Rule {
    private final Pattern pattern;
    private final Callback callback;

    public Rule(Pattern pattern, Callback callback) {
        this.pattern = pattern;
        this.callback = callback;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public boolean match(Task parentTask, Block childBlock){
        if(!pattern.match(childBlock)) return false;
        callback.run(childBlock);
        return true;
    }

    @FunctionalInterface
    public interface Callback {
        public void run(Block child);
    };
}
