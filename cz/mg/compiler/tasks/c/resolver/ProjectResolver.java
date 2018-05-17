//package cz.mg.compiler.tasks.c.resolver;
//
//import cz.mg.compiler.tasks.Task;
//import cz.mg.compiler.entities.c.mg.MgProject;
//
//
//public class ProjectResolver extends Task<MgProject> {
//    public ProjectResolver(Task parent) {
//        super(parent);
//    }
//    
//    @Override
//    protected MgProject onRun() {
//        MgProject project = new MgProject();
//        
//		MgModule buildinModule = project.createBuildinModule("cz.mg.types");
//		buildinModule.add(MgBuildinTypename.BOOL8);
//		buildinModule.add(MgBuildinTypename.CHAR8);
//		buildinModule.add(MgBuildinTypename.FLOAT32);
//		buildinModule.add(MgBuildinTypename.FLOAT64);
//		buildinModule.add(MgBuildinTypename.INT16);
//		buildinModule.add(MgBuildinTypename.INT32);
//		buildinModule.add(MgBuildinTypename.INT64);
//		buildinModule.add(MgBuildinTypename.INT8);
//		buildinModule.add(MgBuildinTypename.UINT16);
//		buildinModule.add(MgBuildinTypename.UINT32);
//		buildinModule.add(MgBuildinTypename.UINT64);
//		buildinModule.add(MgBuildinTypename.UINT8);
//		buildinModule.add(MgBuildinTypename.VOID);
//		buildinModule.add(MgBuildinTypename.STRING);
//		buildinModule = project.createBuildinModule("cz.mg.console");
//		add(new FileComposer(this, new InternalProjectFile("SAMPLE.MG")));
//        
//        todo resolve;
//        
//        return project;
//    }
//}
