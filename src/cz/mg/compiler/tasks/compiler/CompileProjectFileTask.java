package cz.mg.compiler.tasks.compiler;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.Entities;
import cz.mg.compiler.entities.logical.project.Project;
import cz.mg.compiler.entities.structured.Container;
import cz.mg.compiler.entities.text.Page;
import cz.mg.compiler.tasks.builder.project.BuildProjectTask;
import cz.mg.compiler.tasks.composer.ComposePageTask;
import cz.mg.compiler.tasks.input.text.TextInputTask;
import cz.mg.compiler.tasks.input.text.factory.TextInputFactory;
import cz.mg.compiler.tasks.parser.ParsePageTask;
import cz.mg.compiler.utilities.debug.Text;


public class CompileProjectFileTask extends CompilerTask {
    @Info
    private final Text url;

    @Info
    private final TextInputFactory inputFactory;

    @Link
    private final Project project;

    @Link
    private final Entities entities;

    @Part
    private TextInputTask inputTask;

    @Part
    private ParsePageTask parsePageTask;

    @Part
    private ComposePageTask composePageTask;

    @Part
    private BuildProjectTask buildProjectTask;

    public CompileProjectFileTask(Text url, TextInputFactory inputFactory, Project project, Entities entities) {
        this.url = url;
        this.inputFactory = inputFactory;
        this.project = project;
        this.entities = entities;
    }

    @Override
    protected void onRun() {
        inputTask = inputFactory.create(url);
        inputTask.run();

        Page page = new Page(inputTask.getText());
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
