package test;

import cz.mg.compiler.Compiler;
import cz.mg.compiler.cli.Console;
import cz.mg.compiler.cli.Explorer;
import cz.mg.compiler.cli.consoles.WindowConsole;
import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.input.ExternalFileInput;
import cz.mg.compiler.entities.logical.project.FilePath;
import cz.mg.compiler.tasks.compiler.CompileProjectTask;


public class Test {
    public static void main(String[] args) {
        ExternalFileInput.workingDirectory = "/home/me/Plocha/Java/MgCompiler/src/test/project/";

        System.out.print("Compiling... ");

        Compiler compiler = new Compiler(new CompileProjectTask(new FilePath(new Text("TestProject.mg", ""))));
        compiler.run();

        System.out.println("DONE");

        Console console = new WindowConsole();
        Explorer explorer = new Explorer(console, compiler);
        explorer.run();
    }
}
