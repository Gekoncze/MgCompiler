package cz.mg.compiler.cli.utilities;

import cz.mg.collections.Collection;
import cz.mg.collections.list.chainlist.CachedChainList;
import cz.mg.collections.list.chainlist.ChainList;
import java.lang.reflect.Field;


public class ReflectionUtilities extends ElementUtilities {
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
}
