package cz.mg.compilerexplorer;

import cz.mg.collections.Collection;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Compiler;
import cz.mg.compiler.Element;
import cz.mg.compiler.Named;
import cz.mg.compiler.annotations.Child;
import cz.mg.compiler.annotations.Link;
import cz.mg.utilities.ReflectionUtilities;
import java.lang.reflect.Field;


public class Core {
    private final Compiler compiler;
    private final History history;

    public Core(Compiler compiler) {
        this.compiler = compiler;
        this.history = new History(new Node(getObjectType(compiler), "compiler", compiler, true, false));
    }

    public Collection<Node> getState(){
        return getChildren(history.get());
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

    private static Collection<Node> getChildren(Node node) {
        if(node.getElement() instanceof Iterable){
            return getCollectionChildren(node);
        } else {
            return getObjectChildren(node);
        }
    }

    private static Collection<Node> getCollectionChildren(Node node) {
        Iterable element = (Iterable) node.getElement();
        ChainList<Node> allChildren = new ChainList<>();
        for(Object value : element){
            String name = value instanceof Named ? ((Named) value).getName().toString() : "";
            String type = getObjectType(value);
            allChildren.addLast(new Node(type, name, value, node.isChild(), node.isLink()));
        }
        return allChildren;
    }

    private static Collection<Node> getObjectChildren(Node node) {
        Object element = node.getElement();
        ChainList<Node> children = new ChainList();
        for (Field field : ReflectionUtilities.getObjectFields(element)) {
            Object value = ReflectionUtilities.readObjectField(element, field);
            String name = field.getName();
            String type = getObjectType(value);
            if(value != null){
                boolean isChild = field.isAnnotationPresent(Child.class);
                boolean isLink = field.isAnnotationPresent(Link.class);
                boolean isElement = value instanceof Element;
                boolean isCollection = value instanceof Iterable;
                if ((isElement || isCollection) && (isChild || isLink)){
                    children.addLast(new Node(type, name, value, isChild, isLink));
                }
            }
        }
        return children;
    }

    private static String getObjectType(Object element){
        if(element == null) return "Object";
        if(element instanceof Iterable){
            return "Object" + "[]";
        } else {
            return element.getClass().getSimpleName();
        }
    }
}
