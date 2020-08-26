package fluent.statments;

import fluent.Flux;
import fluent.WhereType;
import fluent.fieldholders.WhereConcatOperatorEnum;
import fluent.fieldholders.WhereFieldHolder;

import java.util.function.Function;
import java.util.function.Supplier;

public class WhereStatement<T> extends Statement<T> {

    private Statement<T> nextStatement = null;
    private WhereType whereType;
    private Object one, bottom, top;
    private Object[] in;

    private WhereFieldHolder whereFieldHolder;

    private WhereStatement<T> next;
    private WhereConcatOperatorEnum operator;

    public WhereStatement(Supplier<T> supplier, Function<T, Object> function, Flux<T> flux) {
        super(supplier, flux);
        fieldHolder = getFieldHolder(function);
    }

    public WhereStatement<T> getLast() {
        if (next == null)
            return this;
        return next.getLast();
    }

    public Flux<T> equal(Object value) {
        whereFieldHolder = new WhereFieldHolder(fieldHolder, WhereType.EQUAL, value);
        return flux;
    }

    public Flux<T> lessThen(Object value) {
        whereFieldHolder = new WhereFieldHolder(fieldHolder, WhereType.LESS_THEN, value);
        return flux;
    }

    public Flux<T> greaterThen(Object value) {
        whereFieldHolder = new WhereFieldHolder(fieldHolder, WhereType.GREATER_THEN, value);
        return flux;
    }

    public Flux<T> notEqual(Object value) {
        whereFieldHolder = new WhereFieldHolder(fieldHolder, WhereType.NOT_EQUAL, value);
        return flux;
    }

    public Flux<T> in(Object... values) {
        whereFieldHolder = new WhereFieldHolder(fieldHolder, WhereType.IN, values);
        return flux;
    }

    public Flux<T> between(Object low, Object top) {
        whereFieldHolder = new WhereFieldHolder(fieldHolder, WhereType.BETWEEN, new Object[]{low, top});
        return flux;
    }

    public Flux<T> notBetween(Object low, Object top) {
        whereFieldHolder = new WhereFieldHolder(fieldHolder, WhereType.NOT_BETWEEN, new Object[]{low, top});
        return flux;
    }

    public void setNext(WhereStatement<T> next) {
        this.next = next;
    }

    public void setOperator(WhereConcatOperatorEnum operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {

        switch (whereType) {
//            case EQUAL:
//            case NOT_EQUAL:
//            case LESS_THEN:
//            case GREATER_THEN:
//                return flux.getDialect().getWhereTranslator(whereType, one).translate();
//            case BETWEEN:
//            case NOT_BETWEEN:
//                return flux.getDialect().getWhereTranslator(whereType, bottom, top).translate();
//            case NOT_IN:
//            case IN:
//                return flux.getDialect().getWhereTranslator(whereType, in).translate();
            default:
                return "Error";
        }

    }
}
