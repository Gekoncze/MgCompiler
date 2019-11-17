package cz.mg.compiler.tasks.builder.utilities;

import cz.mg.collections.Collection;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ReflectionUtilities {
    public static Collection<Field> getAllFields(Class c, boolean unlock){
        ChainList<Field> allFields = new CachedChainList<>();
        while(c != null){
            Field[] fields = c.getDeclaredFields();
            for(Field field : fields){
                if(unlock) field.setAccessible(true);
                allFields.addLast(field);
            }
            c = c.getSuperclass();
        }
        return allFields;
    }

    public static boolean objectof(Object object, Class superclass){
        return superclass.isInstance(object);
    }

    public static boolean typeof(Field field, Class superclass){
        return superclass.isAssignableFrom(field.getType());
    }

    public static boolean subclassof(Class subclass, Class superclass){
        return superclass.isAssignableFrom(subclass);
    }

    public static Object readField(Object o, Field field){
        try {
            return field.get(o);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object readField(Object o, String fieldName){
        return readField(o, findField(o.getClass(), fieldName));
    }

    public static void writeField(Object o, Field field, Object value){
        try {
            field.set(o, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeField(Object o, String fieldName, Object value){
        writeField(o, findField(o.getClass(), fieldName), value);
    }

    public static void callMethod(Object o, Method method, Object... arguments){
        try {
            method.invoke(o, arguments);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object create(Constructor constructor, Object... arguments){
        try {
            return constructor.newInstance(arguments);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static Constructor findConstructor(Class c, Class... parameters){
        try {
            return c.getConstructor(parameters);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static Field findField(Class objectClass, String objectFieldName){
        for(Field field : ReflectionUtilities.getAllFields(objectClass, true)){
            if(field.getName().equals(objectFieldName)) return field;
        }
        throw new RuntimeException("Could not find field \"" + objectFieldName + "\" in class \"" + objectClass.getSimpleName() + "\".");
    }
}
