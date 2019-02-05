package cz.mg.compiler;

import cz.mg.compiler.entities.a.segments.Book;
import cz.mg.compiler.entities.a.segments.Page;
import cz.mg.compiler.entities.b.logical.Logic;
import cz.mg.compiler.entities.b.logical.project.LogicalProjectPage;
import cz.mg.compiler.entities.b.logical.project.LogicalProjectSourceFile;
import cz.mg.compiler.entities.b.logical.source.LogicalSourcePage;
import cz.mg.compiler.entities.resources.ExternalFile;
import cz.mg.compiler.entities.resources.InternalFile;
import cz.mg.compiler.entities.resources.Resources;
import cz.mg.compiler.entities.resources.Stream;
import cz.mg.compiler.tasks.AbstractTask;
import cz.mg.compiler.tasks.a.parser.PageParser;
import cz.mg.compiler.tasks.b.composer.project.ProjectPageComposer;
import cz.mg.compiler.tasks.b.composer.source.SourcePageComposer;

import java.io.InputStream;


public class Compiler extends AbstractTask {
    private final Resources resources = new Resources(this, null);
    private final Book book = new Book(this, null);
    private final Logic logic = new Logic(this, null);
    //private final MgProject mgproject = new MgProject(build);
    
    public Compiler(String projectName) {
        super(null);
        new ExternalFile(resources, new Location(projectName, -1, -1, -1, -1), projectName);
    }

    @Override
    protected void onRun() {
        Stream projectStream = (Stream) resources.getChildren().getFirst();
        Page projectPage = new Page(book, projectStream.getLocation());
        new PageParser(this, projectStream, projectPage).run();
        LogicalProjectPage logicalProjectPage = new LogicalProjectPage(logic, projectPage.getLocation());
        new ProjectPageComposer(this, projectPage, logicalProjectPage).run();
        
        for(Object child : logic.getChildren().getFirst()){
            if(child instanceof LogicalProjectSourceFile){
                String name = ((LogicalProjectSourceFile) child).getName();
                Stream sourceStream = new ExternalFile(resources, new Location(name, -1, -1, -1, -1), name);
                Page sourcePage = new Page(book, sourceStream.getLocation());
                new PageParser(this, sourceStream, sourcePage).run();
                new SourcePageComposer(this, sourcePage, new LogicalSourcePage(logic, sourcePage.getLocation())).run();
            }
        }
    }
}
