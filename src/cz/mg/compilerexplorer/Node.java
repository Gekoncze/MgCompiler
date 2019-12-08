package cz.mg.compilerexplorer;


public class Node {
    private final String type;
    private final String name;
    private final Object element;
    private final boolean child;
    private final boolean link;

    public Node(String type, String name, Object element, boolean child, boolean link) {
        this.type = type;
        this.name = name;
        this.element = element;
        this.child = child;
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getElement() {
        return element;
    }

    public boolean isChild() {
        return child;
    }

    public boolean isLink() {
        return link;
    }
}