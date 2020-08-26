package fluent.evaluators;

public enum FieldType {
    INTEGER(new Integer(1),
            new Integer(2)),
    LONG(new Integer(1),
            new Long(2)),
    STRING(new String("Ala ma kota."), new String("A kot ma Ale.")),
    CUSTOM(null, null);

    Object firstValue, changeValue;

    FieldType(Object firstValue, Object changeValue) {
        this.firstValue = firstValue;
        this.changeValue = changeValue;
    }

    public Object getFirstValue() {
        return firstValue;
    }

    public Object getChangeValue() {
        return changeValue;
    }
}
