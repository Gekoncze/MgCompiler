//package cz.mg.compiler.tasks.resolver.language;
//
//import cz.mg.compiler.entities.logical.language.Context;
//import cz.mg.compiler.entities.logical.language.LanguageEntity;
//import cz.mg.compiler.entities.logical.language.Location;
//import cz.mg.compiler.entities.logical.language.definitions.ClassDefinition;
//import cz.mg.compiler.entities.logical.language.definitions.FunctionDefinition;
//import cz.mg.compiler.tasks.Task;
//import cz.mg.compiler.tasks.resolver.ResolveException;
//
//
//public class ResolveLocationTask extends LanguageResolverTask {
//    public ResolveLocationTask(Task parentTask, Location location) {
//        super(parentTask);
//        for(LanguageEntity entity : location.getEntities()){
//            if(entity instanceof Context) new ResolveContextTask(this, (Context)entity);
//            else if(entity instanceof ClassDefinition) new ResolveClassDefinitionTask(this, (ClassDefinition) entity);
//            else if(entity instanceof FunctionDefinition) new ResolveFunctionDefinitionTask(this, (FunctionDefinition) entity);
//            else if(entity instanceof Location) new ResolveLocationTask(this, (Location) entity);
//            else throw new ResolveException(entity, "Unexpected entity of type \"" + entity.getClass().getSimpleName() + "\".");
//        }
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
