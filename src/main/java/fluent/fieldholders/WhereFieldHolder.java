package fluent.fieldholders;

import fluent.WhereType;

public class WhereFieldHolder extends CustomFieldHolder {

    private WhereType whereType;
    private Object values;

    public WhereFieldHolder(FieldHolder fieldHolder, WhereType whereType, Object values) {
        super(fieldHolder);
        this.whereType = whereType;
        this.values = values;
    }




}
