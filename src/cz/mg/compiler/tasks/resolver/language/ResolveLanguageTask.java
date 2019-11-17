//package cz.mg.compiler.tasks.resolver.language;
//
//import cz.mg.compiler.entities.logical.language.Language;
//import cz.mg.compiler.entities.logical.language.Location;
//import cz.mg.compiler.tasks.Task;
//
//
//public class ResolveLanguageTask extends LanguageResolverTask {
//    public ResolveLanguageTask(Task parentTask, Language language) {
//        super(parentTask);
//        for(Location location : language.getLocations()) new ResolveLocationTask(this, location);
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
