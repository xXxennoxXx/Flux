package fluent.fieldholders;

import java.lang.reflect.Field;

public class SelectFieldHolder extends FieldHolder {
    public SelectFieldHolder(String className, Field mainField) {
        super(className, mainField);
    }

    public SelectFieldHolder(String className, Field mainField, Field subField) {
        super(className, mainField, subField);
    }
}
