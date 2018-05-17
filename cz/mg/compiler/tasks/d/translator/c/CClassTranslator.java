package cz.mg.compiler.tasks.d.translator.c;

//package cz.mg.translator.c;
//
//import cz.mg.lang.utilities.error.CompileException;
//import cz.mg.lang.MgAttribute;
//import cz.mg.lang.MgClass;
//import cz.mg.lang.MgFunction;
//import cz.mg.lang.MgModule;
//import cz.mg.lang.MgType;
//import cz.mg.lang.MgTypename;
//import cz.mg.lang.buildintypes.MgBuildinType;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//
//
//public class CClassTranslator {
//    public static void translate(MgClass mgclass) throws IOException, CompileException {
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//        writeHeader(writer, mgclass);
//        writer.write("\n");
//        writeDependencies(writer, mgclass);
//        writer.write("\n");
//        writeStructure(writer, mgclass);
//        writer.write("\n");
//        writeFunctions(writer, mgclass);
//        writer.write("\n");
//        writeFooter(writer, mgclass);
//        writer.flush();
//    }
//    
//    private static void writeHeader(BufferedWriter writer, MgClass mgclass) throws IOException, CompileException {
//        String uppercasePath = (mgclass.getFullName() + ".h").replace(".", "_").toUpperCase();
//        writer.write("#ifndef " + uppercasePath + "\n");
//        writer.write("#define " + uppercasePath + "\n");
//    }
//    
//    private static void writeDependencies(BufferedWriter writer, MgClass mgclass) throws IOException, CompileException {
//        CTypeDependencies dependencies = new CTypeDependencies();
//        for(MgAttribute attribute : mgclass.getAttributes()){
//            dependencies.add(attribute.getType());
//        }
//        for(MgModule buildinDependency : dependencies.getBuildinDependencies()){
//            writer.write("#include \"" + buildinDependency.getFullName().toString("_") + ".h\";\n");
//        }
//        for(MgType dependency : dependencies.getDependencies()){
//            writer.write("#include \"" + dependency.getTypename().getFullName().toString("_") + ".h\";\n");
//        }
//        for(MgType pointerDependency : dependencies.getPointerDependencies()){
//            writer.write("structure " + pointerDependency.getTypename().getFullName().toString("_") + ";\n");
//        }
//    }
//    
//    private static void writeStructure(BufferedWriter writer, MgClass mgclass) throws IOException, CompileException {
//        CDocumentationTranslator.translate(writer, mgclass.getDocumentation());
//        writer.write("struct " + mgclass.getFullName().toString("_") + " {\n");
//        for(MgAttribute attribute : mgclass.getAttributes()){
//            MgType type = attribute.getType();
//            MgTypename typename = type.getTypename();
//            writer.write("\t");
//            if(typename instanceof MgBuildinType){
//                writer.write(typename.getFullName().toString("_"));
//            } else if(typename instanceof MgClass){
//                writer.write("struct " + typename.getFullName().toString("_"));
//            } else {
//                throw new CompileException(attribute.getType(),
//                        "Type ", type.getClass().getSimpleName(), " not yet supported.");
//            }
//            for(int i = 0; i < type.getPointerCount(); i++){
//                writer.write("*");
//            }
//            writer.write(" " + attribute.getName());
//            writer.write(";\n");
//        }
//        writer.write("};\n");
//    }
//    
//    private static void writeFunctions(BufferedWriter writer, MgClass mgclass) throws IOException, CompileException {
//        boolean first = true;
//        for(MgFunction function : mgclass.getFunctions()){
//            if(!first) writer.write("\n");
//            CFunctionTranslator.translateHeader(writer, function);
//            writer.write("\n");
//            CFunctionTranslator.translateBody(writer, function);
//            first = false;
//        }
//    }
//    
//    private static void writeFooter(BufferedWriter writer, MgClass mgclass) throws IOException, CompileException {
//        writer.write("#endif \n");
//    }
//}
