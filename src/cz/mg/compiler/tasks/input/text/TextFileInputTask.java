package cz.mg.compiler.tasks.input.text;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.utilities.debug.Trace;
import cz.mg.compiler.utilities.debug.Traceable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TextFileInputTask extends TextInputTask {
    @Info
    private final ReadonlyText absolutePath;

    @Link
    private final ReadonlyText relativePath;

    public TextFileInputTask(ReadonlyText absolutePath, ReadonlyText relativePath) {
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
    }

    @Override
    protected void onRun() {
        String path = absolutePath.toString() + relativePath.toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder content = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                content.append(line);
                content.append("\n");
            }
            this.text = new Text(content.toString(), path);
        } catch (IOException e) {
            Traceable traceable = relativePath instanceof Text ? (Traceable) relativePath : new Trace();
            throw new CompileException(e, traceable, "Could not load text from file ", absolutePath, relativePath);
        }
    }
}
