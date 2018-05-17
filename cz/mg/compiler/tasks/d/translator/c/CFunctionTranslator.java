package cz.mg.compiler.tasks.d.translator.c;

//package cz.mg.translator.c;
//
//import cz.mg.lang.utilities.error.CompileException;
//import cz.mg.lang.MgFunction;
//import cz.mg.lang.MgVariable;
//import cz.mg.lang.commands.MgCommand;
//import java.io.BufferedWriter;
//import java.io.IOException;
//
//
//public class CFunctionTranslator {
//    public static void translateHeader(BufferedWriter writer, MgFunction function) throws IOException, CompileException {
//        CDocumentationTranslator.translate(writer, function.getDocumentation());
//        MgVariable output = null;
//        for(MgVariable o : function.getOutput()) output = o;
//        if(output == null){
//            writer.write("void");
//        } else {
//            writer.write(output.getType().getTypename().getFullName().toString("_"));
//        }
//        writer.write(" ");
//        writer.write(function.getFullName().toString("_"));
//        writer.write("(");
//        boolean first = true;
//        for(MgVariable i : function.getInput()){
//            if(!first) writer.write(", ");
//            writer.write(i.getType().getTypename().getFullName().toString("_"));
//            writer.write(" ");
//            writer.write(i.getName().toString());
//            first = false;
//        }
//        writer.write(")");
//    }
//    
//    public static void translateBody(BufferedWriter writer, MgFunction function) throws IOException {
//        writer.write("{\n");
////        for(MgCommand command : function.getCommands()){
////            writer.write("\t");
////            writer.write(command.toString());
////            writer.write(";\n");
////        }
//        writer.write("}\n");
//    }
//}
