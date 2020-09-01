package flux.fieldholders;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FieldHolder {

    protected static final Map<String, String> charToClass = new HashMap<>();
    private final String className;
    private String classChar;

    protected final Field mainField;
    private Field subField;

    public FieldHolder(String className, Field mainField) {
        this.className = className;
        this.mainField = mainField;

        classChar = getChar(className);
    }

    public FieldHolder(String className, Field mainField, Field subField) {
        this(className, mainField);
        this.subField = subField;
    }

    public static String getChar(String className) {
        if (charToClass.containsKey(className)) {
            return charToClass.get(className);
        } else {
            String firstLetter = className.substring(0, 1).toLowerCase();
            charToClass.put(className, firstLetter);
            return firstLetter;
        }
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

    public String returnName() {
        if (subField == null)
            return new String(classChar + "." + mainField.getName());
        return new String(getChar(mainField.getType().getSimpleName()) + "." + subField.getName());
    }
}
