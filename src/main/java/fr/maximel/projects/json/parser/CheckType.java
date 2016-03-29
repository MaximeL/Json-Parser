package fr.maximel.projects.json.parser;

import java.lang.reflect.Field;
import java.util.Collection;

public class CheckType {

    private CheckType() {}

    public static ObjectType check(Field field) {
        field.setAccessible(true);
        Class fieldType = field.getType();

        if(blackList(field)) {
            System.out.println("Ignore matched");
            return ObjectType.IGNORE;
        }
        System.out.println("Field type : "+fieldType.getName());
        if(Integer.class.isAssignableFrom(fieldType) ||
                Long.class.isAssignableFrom(fieldType) ||
                fieldType.equals(Long.TYPE) ||
                fieldType.equals(Integer.TYPE)) {
            System.out.println("Integer matched");
            return ObjectType.INTEGER;
        }
        if(Boolean.class.isAssignableFrom(fieldType) ||
                fieldType.equals(Boolean.TYPE)) {
            System.out.println("Boolean matched");
            return ObjectType.BOOLEAN;
        }
        if(String.class.isAssignableFrom(fieldType) ||
                fieldType.equals(Character.TYPE)) {
            System.out.println("String matched");
            return ObjectType.STRING;
        }
        if(Collection.class.isAssignableFrom(fieldType)) {
            System.out.println("Array matched");
            return ObjectType.ARRAY;
        }

        System.out.println("Object matched");
        return ObjectType.OBJECT;

    }

    public static ObjectType check(Object object) {
        Class objectType = object.getClass();
        System.out.println("Object type : "+objectType.getName());
        if(Integer.class.isAssignableFrom(objectType)) {
            System.out.println("Integer matched");
            return ObjectType.INTEGER;
        }
        if(Boolean.class.isAssignableFrom(objectType)) {
            System.out.println("Boolean matched");
            return ObjectType.BOOLEAN;
        }
        if(String.class.isAssignableFrom(objectType)) {
            System.out.println("String matched");
            return ObjectType.STRING;
        }
        if(Collection.class.isAssignableFrom(objectType)) {
            System.out.println("Array matched");
            return ObjectType.ARRAY;
        }

        System.out.println("Object matched");
        return ObjectType.OBJECT;

    }

    private static boolean blackList(Field field) {
        //field starting with _ are JPA added ones
        if(field.getName().charAt(0) == '_') return true;
        //We do not need this. And for now, long are not handled well
        if(field.getName().equals("serialVersionUID")) return true;

        return false;
    }
}
