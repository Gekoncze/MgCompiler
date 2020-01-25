package cz.mg.compiler.tasks.compiler;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.Entities;
import cz.mg.compiler.entities.logical.mg.LogicalMg;
import cz.mg.compiler.entities.structured.Container;
import cz.mg.compiler.entities.text.Page;
import cz.mg.compiler.tasks.builder.language.BuildSourceFileTask;
import cz.mg.compiler.tasks.composer.ComposePageTask;
import cz.mg.compiler.tasks.input.text.TextInputTask;
import cz.mg.compiler.tasks.input.text.factory.TextInputFactory;
import cz.mg.compiler.tasks.parser.ParsePageTask;
import cz.mg.compiler.utilities.debug.Text;


public class CompileSourceFileTask extends CompilerTask {
    @Info
    private final Text url;

    @Info
    private final TextInputFactory inputFactory;

    @Link
    private final LogicalMg logicalMg;

    @Link
    private final Entities entities;

    @Part
    private TextInputTask inputTask;

    @Part
    private ParsePageTask parsePageTask;

    @Part
    private ComposePageTask composePageTask;

    @Part
    private BuildSourceFileTask buildSourceFileTask;

    public CompileSourceFileTask(Text url, TextInputFactory inputFactory, LogicalMg logicalMg, Entities entities) {
        this.url = url;
        this.inputFactory = inputFactory;
        this.logicalMg = logicalMg;
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

        Container container = new Container(page.getContent());
        entities.getStructure().getPages().addLast(container);
        composePageTask = new ComposePageTask(page, container);
        composePageTask.run();

        buildSourceFileTask = new BuildSourceFileTask(container, logicalMg);
        buildSourceFileTask.run();
    }
}
