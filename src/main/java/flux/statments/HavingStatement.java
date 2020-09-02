package flux.statments;

import flux.Flux;
import flux.fieldholders.ConditionsConcatenationType;
import flux.fieldholders.HavingFieldHolder;

import java.util.function.Function;

public class HavingStatement<T> extends Statement<T> {


    public HavingStatement(Flux<T> flux) {
        super(flux);
    }

    public HavingAggregateCondition<T> and(Function<T, Object> function) {
        return flux.andHaving(function);
    }

    public HavingAggregateCondition<T> orHaving(Function<T, Object> function) {
        return flux.orHaving(function);
    }

    public OrderByStatement<T> orderBy(Function<T, Object> function) {
        return flux.orderBy(function);
    }
}
