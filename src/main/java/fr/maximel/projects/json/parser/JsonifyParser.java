package fr.maximel.projects.json.parser;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.util.Collection;

public class JsonifyParser {

    private JsonifyParser() {}

    public static JsonValue parse(Object object) throws IllegalAccessException {

        System.out.println("Begin of parsing");

        ObjectType type = CheckType.check(object);

        System.out.println("Creating builder");
        JsonValue result;
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        System.out.println("Checking input type");
        switch (type) {
            case INTEGER:
                Integer number = (Integer) object;
                System.out.println("Getting JsonNumber");
                result =  objectBuilder.add("number", number).build().getJsonNumber("number");
                return result;
            case BOOLEAN:
                Boolean bool = (Boolean) object;
                System.out.println("Getting Boolean");
                result = (bool) ? JsonValue.TRUE : JsonValue.FALSE;
                return result;
            case STRING:
                Integer string = (Integer) object;
                System.out.println("Getting JsonString");
                result =  objectBuilder.add("string", string).build().getJsonString("string");
                return result;
            case OBJECT:
                System.out.println("Parsing Object");
                result = JsonObjectParser.parse(object);
                return result;
            case ARRAY:
                System.out.println("Parsing array");
                result = JsonArrayParser.parse((Collection<Object>) object);
                return result;
            default:
                System.out.println("Nothing matched");
                return null;
        }
    }
}
