//package cz.mg.compiler.tasks.c.resolver;
//
//import cz.mg.collections.TreeNode;
//import cz.mg.compiler.Build;
//import cz.mg.compiler.tasks.Task;
//import cz.mg.compiler.entities.b.logical.source.LogicalSourceFile;
//import cz.mg.compiler.entities.b.logical.project.LogicalProjectFile;
//import cz.mg.compiler.entities.c.mg.MgProject;
//
//
//public class Resolver extends Task<MgProject> {
//    private final Build build;
//    private final LogicalProjectFile logicalProject;
//    
//    public Resolver(Task parent, Build build, LogicalProjectFile logicalProject) {
//        super(parent);
//        this.build = build;
//        this.logicalProject = logicalProject;
//    }
//    
//    @Override
//    protected MgProject onRun() {
//        MgProject mgproject = new MgProject(build);
//        
//        for(TreeNode fileNode : logicalProject){
//            LogicalSourceFile logicalFile = (LogicalSourceFile) fileNode;
//            resolve file;
//        }
//        
//        return mgproject;
//    }
//}
