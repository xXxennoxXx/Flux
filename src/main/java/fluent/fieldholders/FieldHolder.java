package fluent.fieldholders;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FieldHolder {

    private static final Map<String, String> charToClass = new HashMap<>();
    private final String className;
    private String classChar;

    private final Field mainField;
    private Field subField;

    public FieldHolder(String className, Field mainField) {
        this.className = className;
        this.mainField = mainField;

        if (charToClass.containsKey(className)) {
            classChar = charToClass.get(className);
        } else {
            String firstLetter = className.substring(0, 1).toLowerCase();
            classChar = firstLetter;
            charToClass.put(className, firstLetter);
        }


    }

    public FieldHolder(String className, Field mainField, Field subField) {
        this(className, mainField);
        this.subField = subField;
    }

    public String getClassName() {
        return className;
    }

    public Field getMainField() {
        return mainField;
    }

    public Field getSubField() {
        return subField;
    }

    public void setSubField(Field subField) {
        this.subField = subField;
    }

    public static Map<String, String> getCharToClass() {
        return charToClass;
    }

    public String getClassChar() {
        return classChar;
    }
}
