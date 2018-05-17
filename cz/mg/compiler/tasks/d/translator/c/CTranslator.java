package cz.mg.compiler.tasks.d.translator.c;

//package cz.mg.translator.c;
//
//import cz.mg.lang.MgFunction;
//import cz.mg.lang.MgClass;
//import cz.mg.lang.MgModule;
//import cz.mg.lang.MgProject;
//import cz.mg.lang.utilities.error.CompileException;
//import cz.mg.translator.Translator;
//import java.io.IOException;
//
//
//public class CTranslator extends Translator {
//    public CTranslator(MgProject project) {
//        super(project);
//    }
//    
//    @Override
//    public void run() {
//        try {
//            for(MgModule module : getProject().getModules()){
//                translateModule(module);
//            }
//        } catch(IOException e){
//            throw new CompileException(getProject(), "IO Exception.");
//        }
//    }
//    
//    private void translateModule(MgModule module) throws IOException {
//        for(MgModule submodule : module.getModules()){
//            translateModule(submodule);
//        }
//        for(MgClass mgclass : module.getClasses()){
//            CClassTranslator.translate(mgclass);
//        }
//        for(MgFunction function : module.getFunctions()){
//            translateFunction(function);
//        }
//    }
//    
//    private void translateFunction(MgFunction function){
//        
//    }
//}
