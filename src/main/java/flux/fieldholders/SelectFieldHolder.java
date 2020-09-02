package flux.fieldholders;

import flux.AggregateFunctionType;

public class SelectFieldHolder extends CustomFieldHolder {
    private AggregateFunctionType aggregateFunctionType;

    public SelectFieldHolder(FieldHolder fieldHolder) {
        this(fieldHolder, null);
    }

    public SelectFieldHolder(FieldHolder fieldHolder, AggregateFunctionType aggregateFunctionType) {
        super(fieldHolder);
        this.aggregateFunctionType = aggregateFunctionType;
    }

    public AggregateFunctionType getAggregateFunctionType() {
        return aggregateFunctionType;
    }

    public void setAggregateFunctionType(AggregateFunctionType aggregateFunctionType) {
        this.aggregateFunctionType = aggregateFunctionType;
    }


}
