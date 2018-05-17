package cz.mg.compiler.tasks;

import cz.mg.compiler.Location;
import cz.mg.compiler.entities.Entity;
import java.io.PrintWriter;
import java.io.StringWriter;


public class CompileException extends RuntimeException {
	private final ErrorMessage errorMessage;
    private Exception innerException;
	
    public CompileException(Location location, Object... texts){
		this.errorMessage = new ErrorMessage(location, texts);
        this.innerException = null;
    }
    
    public CompileException(Entity entity, Object... texts){
		this.errorMessage = new ErrorMessage(entity.getLocation(), texts);
        this.innerException = null;
    }

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

    public Exception getInnerException() {
        return innerException;
    }

    public void setInnerException(Exception innerException) {
        this.innerException = innerException;
    }
	
	public String stackTraceToString(){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		if(innerException != null) innerException.printStackTrace(pw);
		else printStackTrace(pw);
		return sw.toString();
	}
    
    public String getText(){
        String text = errorMessage.toString() + "\n";
        if(innerException != null) text += innerException.getClass().getSimpleName() + ": " + innerException.getMessage() + "\n";
        text += "\n" + stackTraceToString();
        return text;
    }
}
