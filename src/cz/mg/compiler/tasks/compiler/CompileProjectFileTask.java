package cz.mg.compiler.tasks.compiler;

import cz.mg.compiler.entities.Entities;
import cz.mg.compiler.entities.input.ExternalFileInput;
import cz.mg.compiler.entities.input.InputEntity;
import cz.mg.compiler.entities.logical.project.FilePath;
import cz.mg.compiler.entities.logical.project.Project;
import cz.mg.compiler.entities.structured.Container;
import cz.mg.compiler.entities.text.Page;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.project.BuildProjectTask;
import cz.mg.compiler.tasks.composer.ComposePageTask;
import cz.mg.compiler.tasks.parser.ParsePageTask;


public class CompileProjectFileTask extends CompilerTask {
    private final FilePath filePath;
    private final Project project;
    private final Entities entities;

    public CompileProjectFileTask(Task parentTask, FilePath filePath, Project project, Entities entities) {
        super(parentTask);
        this.filePath = filePath;
        this.project = project;
        this.entities = entities;
    }

    @Override
    protected void onRun() {
        InputEntity source = new ExternalFileInput(filePath.getPath());
        entities.getInput().getInputs().addLast(source);
        new LoadSourceTask(this, source).run();

        Page page = new Page(source.getText());
        entities.getBook().getPages().addLast(page);
        new ParsePageTask(this, page).run();

        Container structuredPage = new Container(page.getContent());
        entities.getStructure().getPages().addLast(structuredPage);
        new ComposePageTask(this, page, structuredPage).run();

        new BuildProjectTask(this, project, structuredPage).run();
    }
}
