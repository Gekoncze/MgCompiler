package cz.mg.compiler.tasks.compiler;

import cz.mg.compiler.entities.Entities;
import cz.mg.compiler.entities.input.ExternalFileInput;
import cz.mg.compiler.entities.input.InputEntity;
import cz.mg.compiler.entities.logical.language.Language;
import cz.mg.compiler.entities.logical.project.FilePath;
import cz.mg.compiler.entities.structured.StructuredPage;
import cz.mg.compiler.entities.text.Page;
import cz.mg.compiler.tasks.Task;
import cz.mg.compiler.tasks.builder.language.BuildSourceFileTask;
import cz.mg.compiler.tasks.composer.ComposePageTask;
import cz.mg.compiler.tasks.parser.ParsePageTask;


public class CompileSourceFileTask extends CompilerTask {
    private final FilePath filePath;
    private final Language language;
    private final Entities entities;

    public CompileSourceFileTask(Task parentTask, FilePath filePath, Language language, Entities entities) {
        super(parentTask);
        this.filePath = filePath;
        this.language = language;
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

        StructuredPage structuredPage = new StructuredPage(page.getContent());
        entities.getStructuredBook().getPages().addLast(structuredPage);
        new ComposePageTask(this, page, structuredPage).run();

        new BuildSourceFileTask(this, structuredPage, language).run();
    }
}
