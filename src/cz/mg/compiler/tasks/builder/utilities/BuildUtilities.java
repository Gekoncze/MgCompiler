package cz.mg.compiler.tasks.builder.utilities;

import cz.mg.compiler.entities.logical.language.Context;
import cz.mg.compiler.entities.logical.language.links.DatatypeDefinitionLink;
import cz.mg.compiler.entities.logical.language.other.Datatype;
import cz.mg.compiler.entities.structured.parts.Type;
import cz.mg.compiler.tasks.builder.BuildException;
import cz.mg.compiler.utilities.debug.PlaceholderText;
import cz.mg.compiler.utilities.debug.Traceable;
import cz.mg.utilities.ReflectionUtilities;

import java.lang.reflect.Field;


public class BuildUtilities {
    private static void store(Object parent, String parentFieldName, Object task, String taskFieldName){
        Field parentField = ReflectionUtilities.findClassField(parent.getClass(), parentFieldName);
        Field taskField = ReflectionUtilities.findClassField(task.getClass(), taskFieldName);
        boolean parentCollection = ReflectionUtilities.typeof(parentField, cz.mg.collections.list.List.class);
        boolean taskCollection = ReflectionUtilities.typeof(taskField, cz.mg.collections.list.List.class);
        Object value = ReflectionUtilities.readObjectField(task, taskField);
        if(!parentCollection && !taskCollection){
            storeValueInValue(parent, parentField, value);
        } else if(parentCollection && !taskCollection){
            storeValueInCollection(parent, parentField, value);
        } else if(!parentCollection && taskCollection){
            throw new RuntimeException("Cannot store collection (" + task.getClass().getSimpleName() + "." + taskFieldName + ") in value (" + parent.getClass().getSimpleName() + "." + parentFieldName + ").");
        } else { // parentCollection && taskCollection
            storeCollectionInCollection(parent, parentField, value);
        }
    }

    public static void store(Object parent, String parentFieldName, Object value){
        Field parentField = ReflectionUtilities.findClassField(parent.getClass(), parentFieldName);
        boolean parentCollection = ReflectionUtilities.typeof(parentField, cz.mg.collections.list.List.class);
        boolean taskCollection = ReflectionUtilities.objectof(value, cz.mg.collections.list.List.class);
        if(!parentCollection && !taskCollection){
            storeValueInValue(parent, parentField, value);
        } else if(parentCollection && !taskCollection){
            storeValueInCollection(parent, parentField, value);
        } else if(!parentCollection && taskCollection){
            throw new RuntimeException("Cannot store collection (" + value.getClass().getSimpleName() + ") in value (" + parent.getClass().getSimpleName() + "." + parentFieldName + ").");
        } else { // parentCollection && taskCollection
            storeCollectionInCollection(parent, parentField, value);
        }
    }

    private static void storeValueInValue(Object parent, Field parentField, Object value){
        Object oldValue = ReflectionUtilities.readObjectField(parent, parentField);
        if(oldValue != null) throw new BuildException((Traceable) value, "Block has been already defined ", new PlaceholderText((Traceable) oldValue, "here"), ".");
        ReflectionUtilities.writeObjectField(parent, parentField, value);
    }

    private static void storeValueInCollection(Object parent, Field parentField, Object value){
        cz.mg.collections.list.List list = (cz.mg.collections.list.List) ReflectionUtilities.readObjectField(parent, parentField);
        list.addLast(value);
    }

    private static void storeCollectionInCollection(Object parent, Field parentField, Object values){
        cz.mg.collections.list.List src = (cz.mg.collections.list.List) values;
        cz.mg.collections.list.List dst = (cz.mg.collections.list.List) ReflectionUtilities.readObjectField(parent, parentField);
        for(Object value : src){
            dst.addLast(value);
        }
    }

    public static Datatype buildDatatype(Context context, Type type){
        DatatypeDefinitionLink datatypeDefinitionLink = new DatatypeDefinitionLink(context, type.getName().getContent());

        Datatype.Storage storage = null;
        int storageLevel = 0;

        int addressCount = type.getSpecial().getContent().count('&');
        int valueCount = type.getSpecial().getContent().count('$');

        if(addressCount > 0 && valueCount == 0){
            storage = Datatype.Storage.ASSOCIATION;
            storageLevel = addressCount;
        }

        if(addressCount == 0 && valueCount > 0){
            storage = Datatype.Storage.COMPOSITION;
            storageLevel = valueCount;
        }

        if(addressCount == 1 && valueCount == 1){
            storage = Datatype.Storage.AGGREGATION;
            storageLevel = 1;
        }

        if(storageLevel == 0 || storage == null) throw new BuildException(type, "Illegal type. Expected (& > 0, $ = 0) or (& = 0, $ > 0) or (& = 1, $ = 1), given (& = ", addressCount, ", $ = ", valueCount, ").");

        return new Datatype(type.getTrace(), datatypeDefinitionLink, storage, storageLevel);
    }
}
