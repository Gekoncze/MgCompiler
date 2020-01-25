package cz.mg.compiler.tasks.output.text;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Text;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class TextFileOutputTask extends TextOutputTask {
    @Info
    private final String absolutePath;

    @Link
    private final Text relativePath;

    public TextFileOutputTask(String absolutePath, Text relativePath, ReadonlyText text) {
        super(text);
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
    }

    @Override
    protected void onRun() {
        String path = absolutePath + relativePath;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            writer.write(text.toString());
        } catch (IOException e){
            throw new CompileException(e, relativePath, "Could not save text to file ", absolutePath, relativePath);
        }
    }
}
