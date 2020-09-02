package flux.fieldholders;

import flux.ConditionType;

public class WhereFieldHolder extends CustomFieldHolder {

    private ConditionType conditionType;
    private Object values;
    private ConditionsConcatenationType conditionsConcatenationType;

    public WhereFieldHolder(FieldHolder fieldHolder) {
        this(fieldHolder, null, null);
    }

    public WhereFieldHolder(FieldHolder fieldHolder, ConditionType conditionType, Object values) {
        this(fieldHolder, conditionType, values, null);
    }

    public WhereFieldHolder(FieldHolder fieldHolder, ConditionType conditionType, Object values, ConditionsConcatenationType conditionsConcatenationType) {
        super(fieldHolder);
        this.conditionType = conditionType;
        this.values = values;
        this.conditionsConcatenationType = conditionsConcatenationType;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public Object getValues() {
        return values;
    }

    public void setValues(Object values) {
        this.values = values;
    }

    public ConditionsConcatenationType getWhereConcatOperatorEnum() {
        return conditionsConcatenationType;
    }

    public void setWhereConcatOperatorEnum(ConditionsConcatenationType conditionsConcatenationType) {
        this.conditionsConcatenationType = conditionsConcatenationType;
    }
}
