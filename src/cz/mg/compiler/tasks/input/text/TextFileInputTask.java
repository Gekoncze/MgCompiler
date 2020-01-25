package cz.mg.compiler.tasks.input.text;

import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Link;
import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Text;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TextFileInputTask extends TextInputTask {
    @Info
    private final String absolutePath;

    @Link
    private final Text relativePath;

    public TextFileInputTask(String absolutePath, Text relativePath) {
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
    }

    @Override
    protected void onRun() {
        String path = absolutePath + relativePath;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder content = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                content.append(line);
                content.append("\n");
            }
            this.text = new Text(content.toString(), path);
        } catch (IOException e) {
            throw new CompileException(e, relativePath, "Could not load text from file ", absolutePath, relativePath);
        }
    }
}
