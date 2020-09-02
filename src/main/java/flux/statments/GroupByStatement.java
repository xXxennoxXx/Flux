package flux.statments;

import flux.Flux;
import flux.fieldholders.ConditionsConcatenationType;
import flux.fieldholders.FieldHolder;
import flux.fieldholders.HavingFieldHolder;
import flux.fieldholders.OrderByFieldHolder;

import java.util.function.Function;
import java.util.function.Supplier;

public class GroupByStatement<T> extends Statement<T> {
    public GroupByStatement(Flux<T> flux) {
        super(flux);
    }

    public HavingAggregateCondition<T> having(Function<T, Object> function) {
        return flux.having(function);
    }

    public OrderByStatement<T> orderBy(Function<T, Object> function) {
        return flux.orderBy(function);
    }
}
