package cz.mg.compiler.tasks.writer.c;

import cz.mg.compiler.entities.runtime.c.CHeaderFile;
import cz.mg.compiler.tasks.output.text.factory.TextOutputFactory;


public class CHeaderFileWriterTask extends CFileWriterTask {
    public CHeaderFileWriterTask(CHeaderFile cFile, TextOutputFactory outputFactory) {
        super(cFile, outputFactory);
    }

    @Override
    protected void onRun() {
        // TODO
        super.onRun();
        // TODO
    }
}
