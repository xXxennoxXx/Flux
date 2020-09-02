package flux.fieldholders;

import flux.AggregateFunctionType;
import flux.ConditionType;

public class HavingFieldHolder extends CustomFieldHolder {

    private Object values;
    private ConditionType conditionType;
    private ConditionsConcatenationType concatenationOperatorTypeType;
    private AggregateFunctionType aggregateFunctionType;

    public HavingFieldHolder(FieldHolder fieldHolder) {
        super(fieldHolder);
    }

    public Object getValues() {
        return values;
    }

    public void setValues(Object values) {
        this.values = values;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public ConditionsConcatenationType getConcatenationOperatorTypeType() {
        return concatenationOperatorTypeType;
    }

    public void setConcatenationOperatorTypeType(ConditionsConcatenationType concatenationOperatorTypeType) {
        this.concatenationOperatorTypeType = concatenationOperatorTypeType;
    }

    public AggregateFunctionType getAggregateFunctionType() {
        return aggregateFunctionType;
    }

    public void setAggregateFunctionType(AggregateFunctionType aggregateFunctionType) {
        this.aggregateFunctionType = aggregateFunctionType;
    }
}
