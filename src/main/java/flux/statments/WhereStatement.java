package flux.statments;

import flux.Flux;
import flux.ConditionType;
import flux.fieldholders.ConditionsConcatenationType;
import flux.fieldholders.WhereFieldHolder;

import java.util.function.Function;

public class WhereStatement<T> extends Statement<T> {


    public WhereStatement(Flux<T> flux) {
        super(flux);
    }

    public WhereCondition<T> and(Function<T, Object> function) {
        return flux.andWhere(function);
    }

    public WhereCondition<T> or(Function<T, Object> function) {
        return flux.orWhere(function);
    }

    @SafeVarargs
    public final GroupByStatement<T> groupBy(Function<T, Object>... functions) {
        return flux.groupBy(functions);
    }

    public OrderByStatement<T> orderBy(Function<T, Object> function) {
        return flux.orderBy(function);
    }
}
