package cz.mg.compiler.utilities.debug;


public class CompileException extends RuntimeException implements Traceable {
    private final Trace trace;
    private final Object[] messageParts;
    private boolean consumed = false;

    public CompileException(Traceable traceable, Object... messageParts) {
        super(flatten(messageParts));
        this.trace = traceable.getTrace();
        this.messageParts = messageParts;
    }

    public CompileException(Exception e, Traceable traceable, Object... messageParts) {
        super(flatten(messageParts), e);
        this.trace = traceable.getTrace();
        this.messageParts = messageParts;
    }

    public void consume(){
        consumed = true;
    }

    public boolean isConsumed() {
        return consumed;
    }

    @Override
    public Trace getTrace() {
        return trace;
    }

    public Object[] getMessageParts() {
        return messageParts;
    }

    private static String flatten(Object[] messageParts){
        StringBuilder sb = new StringBuilder();
        for(Object o : messageParts) sb.append(o.toString());
        return sb.toString();
    }
}
