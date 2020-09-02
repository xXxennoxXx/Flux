package flux.fieldholders;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldHolder that = (FieldHolder) o;
        if (subField != null && that.subField != null)
            return Objects.equals(className, that.className) &&
                    Objects.equals(classChar, that.classChar) &&
                    Objects.equals(mainField.getName(), that.mainField.getName()) &&
                    Objects.equals(subField.getName(), that.subField.getName());
        else if (subField != null || that.subField != null)
            return false;
        else
            return Objects.equals(className, that.className) &&
                    Objects.equals(classChar, that.classChar) &&
                    Objects.equals(mainField.getName(), that.mainField.getName());

    }

    @Override
    public int hashCode() {
        return Objects.hash(className, classChar, mainField.getName());
    }
}
