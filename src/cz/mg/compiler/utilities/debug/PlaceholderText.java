package cz.mg.compiler.utilities.debug;

public class PlaceholderText implements Traceable {
    private final Trace trace;
    private final String placeholder;

    public PlaceholderText(Traceable trace, String placeholder) {
        this.trace = trace.getTrace();
        this.placeholder = placeholder;
    }

    @Override
    public Trace getTrace() {
        return trace;
    }

    @Override
    public String toString() {
        return placeholder;
    }
}
