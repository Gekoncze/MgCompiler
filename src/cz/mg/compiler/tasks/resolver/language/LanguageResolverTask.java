//package cz.mg.compiler.tasks.resolver.language;
//
//import cz.mg.collections.list.chainlist.CachedChainList;
//import cz.mg.collections.list.chainlist.ChainList;
//import cz.mg.compiler.entities.Entity;
//import cz.mg.compiler.entities.logical.language.NamedLanguageEntity;
//import cz.mg.compiler.tasks.Task;
//import cz.mg.compiler.tasks.resolver.ResolverTask;
//
//
//public abstract class LanguageResolverTask extends ResolverTask {
//    private ResolveType resolveType = null;
//
//    public LanguageResolverTask(Task parentTask) {
//        super(parentTask);
//    }
//
//    public void resolveStamps(){
//        resolveType = ResolveType.STAMPS;
//        tryToRun();
//        resolveType = null;
//        for(Task subtask : getTasks()) if(subtask instanceof LanguageResolverTask) ((LanguageResolverTask)subtask).resolveStamps();
//    }
//
//    public void resolveLinks(){
//        resolveType = ResolveType.LINKS;
//        tryToRun();
//        resolveType = null;
//        for(Task subtask : getTasks()) if(subtask instanceof LanguageResolverTask) ((LanguageResolverTask)subtask).resolveStamps();
//    }
//
//    @Override
//    protected final void onRun() {
//        switch (resolveType){
//            case STAMPS: onResolveStamps(); break;
//            case LINKS: onResolveLinks(); break;
//            default: throw new RuntimeException("Invalid resolve type \"" + resolveType + "\".");
//        }
//    }
//
//    protected abstract void onResolveStamps();
//    protected abstract void onResolveLinks();
//
//    private enum ResolveType {
//        STAMPS,
//        LINKS
//    }
//}
