package cz.mg.compiler.tasks.compiler;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.Entities;
import cz.mg.compiler.entities.logical.mg.LogicalMg;
import cz.mg.compiler.entities.logical.project.FilePath;
import cz.mg.compiler.entities.logical.project.Project;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.compiler.utilities.BuildinStamps;
import cz.mg.compiler.tasks.compiler.utilities.BuildinTypes;
import cz.mg.compiler.tasks.input.text.factory.TextInputFactory;
import cz.mg.compiler.utilities.debug.Text;


public class CompileProjectTask extends Task {
    @Link
    private final Entities entities;

    @Info
    private final Text url;

    @Info
    private final TextInputFactory inputFactory;

    @Part
    private CompileProjectFileTask compileProjectFileTask;

    @Part
    private final ChainList<CompileSourceFileTask> compileSourceFileTasks = new ChainList<>();

    public CompileProjectTask(Entities entities, Text url, TextInputFactory inputFactory) {
        this.entities = entities;
        this.url = url;
        this.inputFactory = inputFactory;
    }

    @Override
    protected void onRun() {
        Project project = entities.getLogic().getProject();
        compileProjectFileTask = new CompileProjectFileTask(url, inputFactory, project, entities);
        compileProjectFileTask.run();

        if(project.getSourceFiles() != null){
            LogicalMg logicalMg = entities.getLogic().getLogicalMg();
            BuildinStamps.addBuildinStamps(logicalMg);
            BuildinTypes.addBuildinTypes(logicalMg);
            for(FilePath filePath : project.getSourceFiles().getFiles()){
                compileSourceFileTasks.addLast(new CompileSourceFileTask(filePath.getPath(), inputFactory, logicalMg, entities));
                compileSourceFileTasks.getLast().run();
            }
        }
    }
}
