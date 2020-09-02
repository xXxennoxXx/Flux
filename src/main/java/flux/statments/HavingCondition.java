package flux.statments;

import flux.ConditionType;
import flux.Flux;
import flux.fieldholders.HavingFieldHolder;

public class HavingCondition<T> extends Condition<T> {

    private final HavingFieldHolder havingFieldHolder;

    public HavingCondition(Flux<T> flux, HavingFieldHolder havingFieldHolder) {
        super(flux);
        this.havingFieldHolder = havingFieldHolder;
    }

    public HavingStatement<T> equal(Object value) {
        havingFieldHolder.setValues(value);
        havingFieldHolder.setConditionType(ConditionType.EQUAL);
        return new HavingStatement<>(flux);
    }

    public HavingStatement<T> notEqual(Object value) {
        havingFieldHolder.setValues(value);
        havingFieldHolder.setConditionType(ConditionType.NOT_EQUAL);
        return new HavingStatement<>(flux);
    }

    public HavingStatement<T> lessThen(Object value) {
        havingFieldHolder.setValues(value);
        havingFieldHolder.setConditionType(ConditionType.LESS_THEN);
        return new HavingStatement<>(flux);
    }

    public HavingStatement<T> greaterThen(Object value) {
        havingFieldHolder.setValues(value);
        havingFieldHolder.setConditionType(ConditionType.GREATER_THEN);
        return new HavingStatement<>(flux);
    }

    public HavingStatement<T> in(Object... values) {
        havingFieldHolder.setValues(values);
        havingFieldHolder.setConditionType(ConditionType.IN);
        return new HavingStatement<>(flux);
    }

    public HavingStatement<T> notIn(Object... values) {
        havingFieldHolder.setValues(values);
        havingFieldHolder.setConditionType(ConditionType.NOT_IN);
        return new HavingStatement<>(flux);
    }

    public HavingStatement<T> between(Object low, Object top) {
        havingFieldHolder.setValues(new Object[]{low, top});
        havingFieldHolder.setConditionType(ConditionType.BETWEEN);
        return new HavingStatement<>(flux);
    }

    public HavingStatement<T> notBetween(Object low, Object top) {
        havingFieldHolder.setValues(new Object[]{low, top});
        havingFieldHolder.setConditionType(ConditionType.NOT_BETWEEN);
        return new HavingStatement<>(flux);
    }
}
