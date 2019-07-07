package cz.mg.compiler.entities.input;

import cz.mg.compiler.utilities.debug.CompileException;
import cz.mg.compiler.utilities.debug.Trace;
import cz.mg.compiler.utilities.debug.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ExternalFileInput extends InputEntity {
    public static String workingDirectory = "";

    private final Text path;
    private Text text = null;

    public ExternalFileInput(Text path) {
        this.path = path;
    }

    @Override
    public Text getText() {
        return text;
    }

    @Override
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(workingDirectory + path.toString()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                content.append(line);
                content.append("\n");
            }
            this.text = new Text(content.toString(), path.toString());
        } catch (IOException e) {
            throw new CompileException(e, path, "Could not load external file " + workingDirectory + path.toString());
        }
    }

    @Override
    public Trace getTrace() {
        return path.getTrace();
    }
}
