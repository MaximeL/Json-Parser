package fr.maximel.projects.json.parser;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.util.Collection;

public class JsonObjectParser {

    public static <T> JsonObject parse(T obj) throws IllegalAccessException {
        System.out.println("Begining of object Parsing");
        JsonObjectBuilder builder = Json.createObjectBuilder();
        Class objectClass = obj.getClass();
        System.out.println("Parsing class : "+objectClass.getName());
        Field[] fields = objectClass.getDeclaredFields();
        System.out.println("Number of fields : "+fields.length);

        for (Field field : fields) {
            System.out.println("Parsing field : "+field.getName());
            addField(builder, field, obj);
        }

        System.out.println("End of loop.");

        JsonObject result = builder.build();
        System.out.println("json = \n"+result.toString());

        return result;
    }

    private static <T> JsonObjectBuilder addField(JsonObjectBuilder builder, Field field, T obj) throws IllegalAccessException {
        field.setAccessible(true);

        ObjectType type = CheckType.check(field);

        switch (type) {
            case INTEGER:
                Integer number = (Integer) field.get(obj);
                System.out.println("value = " + number);
                builder = builder.add(field.getName(), number);
                System.out.println("returning builder");
                return builder;
            case BOOLEAN:
                Boolean bool = (Boolean) field.get(obj);
                System.out.println("value = " + bool);
                builder = builder.add(field.getName(), bool);
                System.out.println("returning builder");
                return builder;
            case STRING:
                String string = (String) field.get(obj);
                System.out.println("value = " + string);
                builder = builder.add(field.getName(), string);
                System.out.println("returning builder");
                return builder;
            case OBJECT:
                JsonObject object = JsonObjectParser.parse(field.get(obj));
                System.out.println("object "+ field.getName() + "created");
                builder = builder.add(field.getName(), object);
                return builder;
            case ARRAY:
                JsonArray array = JsonArrayParser.parse((Collection<Object>) field.get(obj));
                System.out.println("array "+ field.getName() + "created");
                builder = builder.add(field.getName(), array);
                return builder;
            case IGNORE:
                System.out.println("ignoring...");
                return builder;
            default:
                return builder;
        }
    }
}
