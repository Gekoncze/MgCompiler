package cz.mg.compiler.tasks.writer.c;

import cz.mg.collections.text.Text;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.runtime.c.CFile;
import cz.mg.compiler.entities.runtime.c.CInclude;
import cz.mg.compiler.entities.runtime.c.definitions.CDefinition;
import cz.mg.compiler.tasks.output.text.TextOutputTask;
import cz.mg.compiler.tasks.output.text.factory.TextOutputFactory;


public class CFileWriterTask extends CWriterTask {
    @Link
    private final CFile cFile;

    @Info
    private final TextOutputFactory outputFactory;

    @Part
    private TextOutputTask outputTask = null;

    public CFileWriterTask(CFile cFile, TextOutputFactory outputFactory) {
        this.cFile = cFile;
        this.outputFactory = outputFactory;
    }

    @Override
    protected void onRun() {
        Text code = new Text();

        if(cFile.getLicence() != null){
            code.append(cFile.getLicence());
        }

        for(CInclude cInclude : cFile.getIncludes()){
            // TODO
        }

        for(CDefinition cDefinition : cFile.getDefinitions()){
            // TODO
        }

        outputTask = outputFactory.create(cFile.getName(), code);
    }
}
