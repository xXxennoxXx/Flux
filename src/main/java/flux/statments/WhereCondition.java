package flux.statments;

import flux.ConditionType;
import flux.Flux;
import flux.fieldholders.WhereFieldHolder;

public class WhereCondition<T> extends Condition<T> {

    private WhereFieldHolder whereFieldHolder;

    public WhereCondition(Flux<T> flux) {
        super(flux);
    }

    public WhereCondition(Flux<T> flux, WhereFieldHolder whereFieldHolder) {
        super(flux);
        this.whereFieldHolder = whereFieldHolder;
    }

    public WhereStatement<T> equal(Object value) {
        whereFieldHolder.setValues(value);
        whereFieldHolder.setConditionType(ConditionType.EQUAL);
        return new WhereStatement<>(flux);
    }

    public WhereStatement<T> notEqual(Object value) {
        whereFieldHolder.setValues(value);
        whereFieldHolder.setConditionType(ConditionType.NOT_EQUAL);
        return new WhereStatement<>(flux);
    }

    public WhereStatement<T> lessThen(Object value) {
        whereFieldHolder.setValues(value);
        whereFieldHolder.setConditionType(ConditionType.LESS_THEN);
        return new WhereStatement<>(flux);
    }

    public WhereStatement<T> greaterThen(Object value) {
        whereFieldHolder.setValues(value);
        whereFieldHolder.setConditionType(ConditionType.GREATER_THEN);
        return new WhereStatement<>(flux);
    }

    public WhereStatement<T> in(Object... values) {
        whereFieldHolder.setValues(values);
        whereFieldHolder.setConditionType(ConditionType.IN);
        return new WhereStatement<>(flux);
    }

    public WhereStatement<T> notIn(Object... values) {
        whereFieldHolder.setValues(values);
        whereFieldHolder.setConditionType(ConditionType.NOT_IN);
        return new WhereStatement<>(flux);
    }

    public WhereStatement<T> between(Object low, Object top) {
        whereFieldHolder.setValues(new Object[]{low, top});
        whereFieldHolder.setConditionType(ConditionType.BETWEEN);
        return new WhereStatement<>(flux);
    }

    public WhereStatement<T> notBetween(Object low, Object top) {
        whereFieldHolder.setValues(new Object[]{low, top});
        whereFieldHolder.setConditionType(ConditionType.NOT_BETWEEN);
        return new WhereStatement<>(flux);
    }
}
