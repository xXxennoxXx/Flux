package flux.fieldholders;

public abstract class CustomFieldHolder {

    protected final FieldHolder fieldHolder;

    public CustomFieldHolder(FieldHolder fieldHolder) {
        this.fieldHolder = fieldHolder;
    }

    public FieldHolder getFieldHolder() {
        return fieldHolder;
    }

    public String returnName() {
        return fieldHolder.returnName();
    }
}
