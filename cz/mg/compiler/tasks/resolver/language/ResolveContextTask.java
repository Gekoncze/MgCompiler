//package cz.mg.compiler.tasks.resolver.language;
//
//import cz.mg.compiler.entities.logical.language.Context;
//import cz.mg.compiler.entities.logical.language.usings.Using;
//import cz.mg.compiler.entities.logical.language.usings.UsingAlias;
//import cz.mg.compiler.entities.logical.language.usings.UsingAll;
//import cz.mg.compiler.tasks.Task;
//
//
//public class ResolveContextTask extends LanguageResolverTask {
//    public ResolveContextTask(Task parentTask, Context context) {
//        super(parentTask);
//        for(Using using : context.getUsings()) new ResolveUsingTask(this, using);
//        for(UsingAll usingAll : context.getUsingsAll()) new ResolveUsingAllTask(this, usingAll);
//        for(UsingAlias usingAlias : context.getUsingsAlias()) new ResolveUsingAliasTask(this, usingAlias);
//    }
//
//    @Override
//    protected void onResolveStamps() {
//    }
//
//    @Override
//    protected void onResolveLinks() {
//    }
//}
