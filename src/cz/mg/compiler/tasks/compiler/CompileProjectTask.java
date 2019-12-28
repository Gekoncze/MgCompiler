package cz.mg.compiler.tasks.compiler;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.Entities;
import cz.mg.compiler.entities.logical.language.Language;
import cz.mg.compiler.entities.logical.project.FilePath;
import cz.mg.compiler.entities.logical.project.Project;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.compiler.utilities.BuildinStamps;
import cz.mg.compiler.tasks.compiler.utilities.BuildinTypes;


public class CompileProjectTask extends Task {
    @Link
    private final Entities entities;

    @Link
    private final FilePath projectFilePath;

    @Part
    private CompileProjectFileTask compileProjectFileTask;

    @Part
    private final ChainList<CompileSourceFileTask> compileSourceFileTasks = new ChainList<>();

    public CompileProjectTask(Entities entities, FilePath projectFilePath) {
        this.entities = entities;
        this.projectFilePath = projectFilePath;
    }

    @Override
    protected void onRun() {
        Project project = entities.getLogic().getProject();
        compileProjectFileTask = new CompileProjectFileTask(projectFilePath, project, entities);
        compileProjectFileTask.run();

        if(project.getSourceFiles() != null){
            Language language = entities.getLogic().getLanguage();
            BuildinStamps.addBuildinStamps(language);
            BuildinTypes.addBuildinTypes(language);
            for(FilePath filePath : project.getSourceFiles().getFiles()){
                compileSourceFileTasks.addLast(new CompileSourceFileTask(filePath, language, entities));
                compileSourceFileTasks.getLast().run();
            }
        }
    }
}
