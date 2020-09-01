package flux.fieldholders;

import flux.OrderByType;

import java.lang.reflect.Field;

public class OrderByFieldHolder extends FieldHolder {

    private OrderByType orderByType;

    public OrderByFieldHolder(String className, Field mainField) {
        super(className, mainField);
    }

    public OrderByFieldHolder(String className, Field mainField, Field subField) {
        super(className, mainField, subField);
    }

    public void setOrderByType(OrderByType orderByType) {
        this.orderByType = orderByType;
    }
}
