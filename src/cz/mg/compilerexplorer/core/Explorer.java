package cz.mg.compilerexplorer.core;

import cz.mg.collections.Collection;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.annotations.Info;
import cz.mg.compiler.annotations.Part;
import cz.mg.compiler.annotations.Link;
import cz.mg.utilities.ReflectionUtilities;
import java.lang.reflect.Field;


public class Explorer {
    private final History history;
    private final Cache cache;

    public Explorer(Node root) {
        this.history = new History(root);
        this.cache = new Cache();
    }

    public History getHistory() {
        return history;
    }

    public State getState(){
        return new State(
                getNodes(history.get(), Part.class),
                getNodes(history.get(), Info.class),
                getNodes(history.get(), Link.class)
        );
    }

    public void open(Node node){
        history.open(node);
    }

    public void back(){
        history.back();
    }

    public void forward(){
        history.forward();
    }

    private static Collection<Node> getNodes(Node node, Class annotation) {
        Object element = node.getElement();
        ChainList<Node> nodes = new ChainList();
        for (Field field : ReflectionUtilities.getObjectFields(element)) {
            Object value = ReflectionUtilities.readObjectField(element, field);
            String name = field.getName();
            if(value != null){
                boolean include = field.isAnnotationPresent(annotation);
                if(include){
                    if(value instanceof Iterable){
                        int i = 0;
                        for(Object object : (Iterable)value){
                            nodes.addLast(new Node(name + "[" + i + "]", object));
                            i++;
                        }
                    } else {
                        nodes.addLast(new Node(name, value));
                    }
                }
            }
        }
        return nodes;
    }

    private static Collection<Node> getPath(Node node){
        throw new UnsupportedOperationException("TODO"); // TODO
    }
}
