package flux.fieldholders;

import flux.OrderByType;

public class OrderByFieldHolder extends CustomFieldHolder {

    private OrderByType orderByType;

    public OrderByFieldHolder(FieldHolder fieldHolder) {
        this(fieldHolder, null);
    }

    public OrderByFieldHolder(FieldHolder fieldHolder, OrderByType orderByType) {
        super(fieldHolder);
        this.orderByType = orderByType;
    }

    public OrderByType getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(OrderByType orderByType) {
        this.orderByType = orderByType;
    }
}
