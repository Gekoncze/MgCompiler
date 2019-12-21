package cz.mg.compilerexplorer.core;


public class Node {
    private final String name;
    private final Object element;
    private int selectedChildIndex = 0;

    public Node(String name, Object element) {
        this.name = name;
        this.element = element;
    }

    public String getName() {
        return name;
    }

    public Object getElement() {
        return element;
    }

    public int getSelectedChildIndex() {
        return selectedChildIndex;
    }

    public void setSelectedChildIndex(int selectedChildIndex) {
        this.selectedChildIndex = selectedChildIndex;
    }
}