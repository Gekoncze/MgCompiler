package cz.mg.compiler;

import cz.mg.compiler.utilities.debug.Text;
import cz.mg.compiler.entities.input.ExternalFileInput;
import cz.mg.compiler.entities.logical.project.FilePath;
import cz.mg.compiler.tasks.compiler.CompileProjectTask;
import cz.mg.compilerexplorer.CompilerExplorer;


public class Test {
    public static void main(String[] args) {
        ExternalFileInput.workingDirectory = "/home/me/Plocha/Dev/Java/MgCompiler/test/cz/mg/compiler/project/";

        System.out.print("Compiling... ");

        Compiler compiler = new Compiler(new CompileProjectTask(new FilePath(new Text("TestProject.mg", ""))));
        compiler.run();

        System.out.println("DONE");

        CompilerExplorer compilerExplorer = new CompilerExplorer(compiler);
        compilerExplorer.setVisible(true);
    }
}
