package cz.mg.compiler.cli.utilities;

import cz.mg.collections.Collection;
import cz.mg.collections.list.List;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import cz.mg.compiler.Element;
import cz.mg.compiler.Child;
import cz.mg.compiler.cli.Utilities;

import java.lang.reflect.Field;


public class ElementUtilities {
    public static Element findElement(int id, Element element){
        if(element.getId() == id) return element;
        for(Element child : getChildElements(element)){
            Element result = findElement(id, child);
            if(result != null) return result;
        }
        return null;
    }

    public static Element findParentElement(int id, Element element){
        for(Element child : getChildElements(element)){
            if(child.getId() == id) return element;
            Element result = findParentElement(id, child);
            if(result != null) return result;
        }
        return null;
    }

    public static List<Element> getChildElements(Element element){
        ChainList<Element> allChildren = new CachedChainList<>();

        for(Field field : Utilities.getAllFields(element.getClass(), true)){
            if(Utilities.typeof(field, Element.class)){
                if(field.isAnnotationPresent(Child.class)){
                    Element child = (Element) Utilities.readField(element, field);
                    if(child != null) allChildren.addLast(child);
                }
            }

            if(Utilities.typeof(field, Collection.class)){
                Collection children = (Collection) Utilities.readField(element, field);
                if(children != null){
                    for(Object child : children){
                        if(child instanceof Element){
                            if(field.isAnnotationPresent(Child.class)){
                                if(child != null) allChildren.addLast((Element)child);
                            }
                        }
                    }
                }
            }
        }

        return allChildren;
    }
}
