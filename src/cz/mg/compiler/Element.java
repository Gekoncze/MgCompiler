package cz.mg.compiler;


public class Element {
    private static int NEXT_ID = 0;

    private static synchronized int nextId(){
        return NEXT_ID++;
    }

    private final int id = nextId();

    public Element() {
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "#" + id;
    }
}
