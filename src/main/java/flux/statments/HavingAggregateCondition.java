package flux.statments;

import flux.AggregateFunctionType;
import flux.ConditionType;
import flux.Flux;
import flux.fieldholders.HavingFieldHolder;

public class HavingAggregateCondition<T> extends Condition<T> {
    private final HavingFieldHolder havingFieldHolder;

    public HavingAggregateCondition(Flux<T> flux, HavingFieldHolder havingFieldHolder) {
        super(flux);
        this.havingFieldHolder = havingFieldHolder;
    }

    public HavingStatement<T> max() {
        havingFieldHolder.setAggregateFunctionType(AggregateFunctionType.MAX);
        return new HavingStatement<>(flux);
    }

    public HavingStatement<T> min() {
        havingFieldHolder.setAggregateFunctionType(AggregateFunctionType.MIN);
        return new HavingStatement<>(flux);
    }

    public HavingStatement<T> avg() {
        havingFieldHolder.setAggregateFunctionType(AggregateFunctionType.AVG);
        return new HavingStatement<>(flux);
    }

    public HavingStatement<T> equal(Object value) {
        return createHavingCondition().equal(value);
    }

    public HavingStatement<T> notEqual(Object value) {
        return createHavingCondition().notEqual(value);
    }

    public HavingStatement<T> lessThen(Object value) {
        return createHavingCondition().lessThen(value);
    }

    public HavingStatement<T> greaterThen(Object value) {
        return createHavingCondition().greaterThen(value);
    }

    public HavingStatement<T> in(Object... values) {
        return createHavingCondition().in(values);
    }

    public HavingStatement<T> notIn(Object... values) {
        return createHavingCondition().notIn(values);
    }

    public HavingStatement<T> between(Object low, Object top) {
        return createHavingCondition().between(low, top);
    }

    public HavingStatement<T> notBetween(Object low, Object top) {
        return createHavingCondition().notBetween(low, top);
    }

    private HavingCondition<T> createHavingCondition() {
        return new HavingCondition<>(flux, havingFieldHolder);
    }

}
