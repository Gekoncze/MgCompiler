package cz.mg.compiler.tasks.compiler;

import cz.mg.compiler.entities.logical.language.Language;
import cz.mg.compiler.entities.logical.project.FilePath;
import cz.mg.compiler.entities.logical.project.Project;
import cz.mg.compiler.tasks.MainTask;
import cz.mg.compiler.tasks.compiler.utilities.BuildinStamps;
import cz.mg.compiler.tasks.compiler.utilities.BuildinTypes;


public class CompileProjectTask extends MainTask {
    private final FilePath projectFilePath;

    public CompileProjectTask(FilePath projectFilePath) {
        this.projectFilePath = projectFilePath;
    }

    @Override
    protected void onRun() {
        Project project = getEntities().getLogic().getProject();
        new CompileProjectFileTask(this, projectFilePath, project, getEntities()).run();

        if(project.getSourceFiles() != null){
            Language language = getEntities().getLogic().getLanguage();
            BuildinStamps.addBuildinStamps(language);
            BuildinTypes.addBuildinTypes(language);
            for(FilePath filePath : project.getSourceFiles().getFiles()){
                new CompileSourceFileTask(this, filePath, language, getEntities()).run();
            }
        }
    }
}
