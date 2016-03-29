package fr.maximel.projects.json.parser;

public enum ObjectType {
    INTEGER(0),
    STRING(1),
    BOOLEAN(2),
    OBJECT(3),
    ARRAY(4),
    IGNORE(5);

    private int value;

    ObjectType(int value) {
        this.value = value;
    }
}
