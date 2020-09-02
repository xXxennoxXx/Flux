package flux.statments;

import flux.Flux;
import flux.OrderByType;
import flux.fieldholders.FieldHolder;
import flux.fieldholders.OrderByFieldHolder;

import java.util.function.Function;

public class OrderByStatement<T> extends Statement<T> {

    private OrderByFieldHolder orderByFieldHolder;

    public OrderByStatement(Flux<T> flux, OrderByFieldHolder orderByFieldHolder) {
        super(flux);
        this.orderByFieldHolder = orderByFieldHolder;
    }

    public OrderByStatement<T> orderBy(Function<T, Object> function) {
        return flux.orderBy(function);
    }

    public OrderByStatement<T> asc() {
        orderByFieldHolder.setOrderByType(OrderByType.ASC);
        return this;
    }

    public OrderByStatement<T> ascending() {
        return asc();
    }

    public OrderByStatement<T> desc() {
        orderByFieldHolder.setOrderByType(OrderByType.DESC);
        return this;
    }

    public OrderByStatement<T> descending() {
        return desc();
    }


}
