package cz.mg.compiler.tasks.compiler;

import cz.mg.compiler.entities.Entities;
import cz.mg.compiler.entities.input.ExternalFileInput;
import cz.mg.compiler.entities.input.InputEntity;
import cz.mg.compiler.entities.logical.language.Language;
import cz.mg.compiler.entities.logical.project.FilePath;
import cz.mg.compiler.entities.structured.Container;
import cz.mg.compiler.entities.text.Page;
import cz.mg.compiler.tasks.builder.language.BuildSourceFileTask;
import cz.mg.compiler.tasks.composer.ComposePageTask;
import cz.mg.compiler.tasks.parser.ParsePageTask;


public class CompileSourceFileTask extends CompilerTask {
    private final FilePath filePath;
    private final Language language;
    private final Entities entities;

    public CompileSourceFileTask(FilePath filePath, Language language, Entities entities) {
        this.filePath = filePath;
        this.language = language;
        this.entities = entities;
    }

    @Override
    protected void onRun() {
        InputEntity source = new ExternalFileInput(filePath.getPath());
        entities.getInput().getInputs().addLast(source);
        new LoadSourceTask(source).run();

        Page page = new Page(source.getText());
        entities.getBook().getPages().addLast(page);
        new ParsePageTask(page).run();

        Container structuredPage = new Container(page.getContent());
        entities.getStructure().getPages().addLast(structuredPage);
        new ComposePageTask(page, structuredPage).run();

        new BuildSourceFileTask(structuredPage, language).run();
    }
}
