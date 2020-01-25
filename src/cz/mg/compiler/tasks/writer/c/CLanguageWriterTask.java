package cz.mg.compiler.tasks.writer.c;

import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.entities.runtime.c.CFile;
import cz.mg.compiler.entities.runtime.c.CHeaderFile;
import cz.mg.compiler.entities.runtime.c.CLanguage;
import cz.mg.compiler.tasks.output.text.factory.TextOutputFactory;


public class CLanguageWriterTask extends CWriterTask {
    @Link
    private final CLanguage cLanguage;

    @Info
    private final TextOutputFactory outputFactory;

    @Part
    private final ChainList<CFileWriterTask> fileWriterTasks = new ChainList<>();

    public CLanguageWriterTask(CLanguage cLanguage, TextOutputFactory outputFactory) {
        this.cLanguage = cLanguage;
        this.outputFactory = outputFactory;
    }

    @Override
    protected void onRun() {
        for(CFile cFile : cLanguage.getFiles()){
            if(cFile instanceof CHeaderFile){
                fileWriterTasks.addLast(new CHeaderFileWriterTask((CHeaderFile) cFile, outputFactory));
                fileWriterTasks.getLast().run();
            } else {
                fileWriterTasks.addLast(new CFileWriterTask(cFile, outputFactory));
                fileWriterTasks.getLast().run();
            }
        }
    }
}
