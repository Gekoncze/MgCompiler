package cz.mg.compiler.tasks.a.parser;

import cz.mg.compiler.entities.resources.Stream;
import cz.mg.compiler.tasks.CompileException;
import java.io.InputStreamReader;


public class StreamReader {
    private static final int FILE_SIZE_LIMIT = 10000000;
    
    private final Stream stream;
    private final InputStreamReader reader;
    private int currentPageSize = 0;
    //private int currentLineNumber = 0;


    public StreamReader(Stream stream) {
        try {
            this.stream = stream;
            this.reader = new InputStreamReader(stream.getStream());
        } catch(Exception e){
            CompileException ex = new CompileException(stream.getLocation(), "Unexpected error while opening stream: " + e.getClass().getSimpleName() + ": " + e.getMessage());
            ex.setInnerException(e);
            throw ex;
        }
    }
    
    public String readLine(){
        try {
			StringBuilder builder = new StringBuilder();
			boolean read = false;
			int value;
			while((value = reader.read()) >= 0){
				currentPageSize++;
				if(currentPageSize > FILE_SIZE_LIMIT) throw new CompileException(stream.getLocation(), "Page size is too big. Limit is " + FILE_SIZE_LIMIT + " bytes.");
				read = true;
				char ch = (char) value;
				if(ch == '\n'){
					break;
				} else {
					builder.append(ch);
				}
			}

			if(read){
				//currentLineNumber++;
				return builder.toString();
			} else {
				return null;
			}
		} catch(CompileException e){
			throw e;
		} catch(Exception e){
			CompileException ex = new CompileException(stream.getLocation(), "Unexpected error while reading data from stream: ", e.getMessage());
            ex.setInnerException(e);
            throw ex;
        }
    }

    public void close() {
        try {
            reader.close();
        } catch(CompileException e){
			throw e;
		} catch(Exception e){
            CompileException ex = new CompileException(stream.getLocation(), "Unexpected error while closing stream: ", e.getMessage());
            ex.setInnerException(e);
            throw ex;
		}
    }
}
