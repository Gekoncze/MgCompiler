package cz.mg.compiler.tasks.output.text;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;
import cz.mg.compiler.utilities.debug.Traceable;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class TextFileOutputTask extends TextOutputTask {
    @Info
    private final ReadonlyText absolutePath;

    @Link
    private final ReadonlyText relativePath;

    public TextFileOutputTask(ReadonlyText absolutePath, ReadonlyText relativePath, ReadonlyText text) {
        super(text);
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
    }

    @Override
    protected void onRun() {
        String path = absolutePath.toString() + relativePath.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            writer.write(text.toString());
        } catch (IOException e){
            Traceable traceable = relativePath instanceof Text ? (Traceable) relativePath : new Trace();
            throw new CompileException(e, traceable, "Could not save text to file ", absolutePath, relativePath);
        }
    }
}
