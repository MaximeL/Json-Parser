package fr.maximel.projects.json.parser;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.Collection;

public class JsonArrayParser {

    public static JsonArray parse(Collection<Object> coll) throws IllegalAccessException {
        System.out.println("Begining of array Parsing");
        JsonArrayBuilder builder = Json.createArrayBuilder();
        Class objectClass = coll.getClass();
        System.out.println("Parsing collection : "+objectClass.getName());
        System.out.println("Number of elements : "+coll.size());

        for (Object element : coll) {
            System.out.println("Parsing element ");
            addField(builder, element);
        }

        System.out.println("End of loop.");

        JsonArray result = builder.build();
        System.out.println("json = \n"+result.toString());

        return result;
    }

    private static JsonArrayBuilder addField(JsonArrayBuilder builder, Object element) throws IllegalAccessException {
        ObjectType type = CheckType.check(element);

        switch (type) {
            case INTEGER:
                Integer number = (Integer) element;
                System.out.println("value = " + number);
                builder = builder.add(number);
                System.out.println("returning builder");
                return builder;
            case BOOLEAN:
                Boolean bool = (Boolean) element;
                System.out.println("value = " + bool);
                builder = builder.add(bool);
                System.out.println("returning builder");
                return builder;
            case STRING:
                String string = (String) element;
                System.out.println("value = " + string);
                builder = builder.add(string);
                System.out.println("returning builder");
                return builder;
            case OBJECT:
                JsonObject object = JsonObjectParser.parse(element);
                System.out.println("object created");
                builder = builder.add(object);
                return builder;
            case ARRAY:
                JsonArray array = JsonArrayParser.parse((Collection<Object>) element);
                System.out.println("array created");
                builder = builder.add(array);
                return builder;
            case IGNORE:
                System.out.println("ignoring...");
                return builder;
            default:
                return builder;
        }
    }
}
