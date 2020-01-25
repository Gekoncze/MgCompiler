package cz.mg.compiler.tasks.writer.c;

import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.entities.runtime.c.CLanguage;
import cz.mg.compiler.tasks.writer.WriterTask;


public class CWriterTask extends WriterTask {
    @Link
    private final CLanguage cLanguage;

    public CWriterTask(CLanguage cLanguage) {
        this.cLanguage = cLanguage;
    }

    @Override
    protected void onRun() {
        //todo;
    }
}
