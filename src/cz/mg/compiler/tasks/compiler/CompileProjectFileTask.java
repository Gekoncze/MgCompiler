package cz.mg.compiler.tasks.compiler;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.Entities;
import cz.mg.compiler.entities.input.ExternalFileInput;
import cz.mg.compiler.entities.input.InputEntity;
import cz.mg.compiler.entities.logical.project.FilePath;
import cz.mg.compiler.entities.logical.project.Project;
import cz.mg.compiler.entities.structured.Container;
import cz.mg.compiler.entities.text.Page;
import cz.mg.compiler.tasks.builder.project.BuildProjectTask;
import cz.mg.compiler.tasks.composer.ComposePageTask;
import cz.mg.compiler.tasks.parser.ParsePageTask;


public class CompileProjectFileTask extends CompilerTask {
    @Link
    private final FilePath filePath;

    @Link
    private final Project project;

    @Link
    private final Entities entities;

    @Part
    private LoadSourceTask loadSourceTask;

    @Part
    private ParsePageTask parsePageTask;

    @Part
    private ComposePageTask composePageTask;

    @Part
    private BuildProjectTask buildProjectTask;

    public CompileProjectFileTask(FilePath filePath, Project project, Entities entities) {
        this.filePath = filePath;
        this.project = project;
        this.entities = entities;
    }

    @Override
    protected void onRun() {
        InputEntity source = new ExternalFileInput(filePath.getPath());
        entities.getInput().getInputs().addLast(source);
        loadSourceTask = new LoadSourceTask(source);
        loadSourceTask.run();

        Page page = new Page(source.getText());
        entities.getBook().getPages().addLast(page);
        parsePageTask = new ParsePageTask(page);
        parsePageTask.run();

        Container structuredPage = new Container(page.getContent());
        entities.getStructure().getPages().addLast(structuredPage);
        composePageTask = new ComposePageTask(page, structuredPage);
        composePageTask.run();

        buildProjectTask = new BuildProjectTask(project, structuredPage);
        buildProjectTask.run();
    }
}
