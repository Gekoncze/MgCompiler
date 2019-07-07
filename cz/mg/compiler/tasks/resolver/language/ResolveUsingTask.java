package cz.mg.compiler.tasks.resolver.language;

import cz.mg.compiler.entities.logical.language.Language;
import cz.mg.compiler.entities.logical.language.usings.UsingDirect;
import cz.mg.compiler.tasks.Task;


public class ResolveUsingTask extends LanguageResolverTask {
    private final UsingDirect using;
    private final Language language;

    public ResolveUsingTask(Task parentTask, UsingDirect using, Language language) {
        super(parentTask);
        this.using = using;
        this.language = language;
    }

    @Override
    protected void onResolveStamps() {

    }

    @Override
    protected void onResolveLinks() {
    }
}
