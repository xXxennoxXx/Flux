package flux.fieldholders;

public class CustomFieldHolder {

    protected final FieldHolder fieldHolder;

    public CustomFieldHolder(FieldHolder fieldHolder) {
        this.fieldHolder = fieldHolder;
    }

    public FieldHolder getFieldHolder() {
        return fieldHolder;
    }
}
