package flux.statments;

import flux.AggregateFunctionType;
import flux.Flux;
import flux.JoinType;
import flux.fieldholders.*;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

public class SelectStatement<T> extends Statement<T> {

    public SelectStatement(Flux<T> flux) {
        super(flux);
    }

    @SafeVarargs
    public final SelectStatement<T> max(Function<T, Object>... functions) {
        return flux.select(AggregateFunctionType.MAX, functions);
    }

    @SafeVarargs
    public final SelectStatement<T> min(Function<T, Object>... functions) {
        return flux.select(AggregateFunctionType.MIN, functions);
    }

    @SafeVarargs
    public final SelectStatement<T> avg(Function<T, Object>... functions) {
        return flux.select(AggregateFunctionType.AVG, functions);
    }

    @SafeVarargs
    public final FromStatement<T> from(Function<T, Object>... functions) {
        return flux.from(functions);
    }


    @SafeVarargs
    public final JoinStatement<T> join(Function<T, Object>... functions) {
        return flux.join(functions);
    }

    @SafeVarargs
    public final JoinStatement<T> leftJoin(Function<T, Object>... functions) {
        return flux.leftJoin(functions);

    }

    @SafeVarargs
    public final JoinStatement<T> rightJoin(Function<T, Object>... functions) {
        return flux.rightJoin(functions);

    }

    @SafeVarargs
    public final JoinStatement<T> fullJoin(Function<T, Object>... functions) {
        return flux.fullJoin(functions);
    }


    public WhereCondition<T> where(Function<T, Object> function) {
        return flux.where(function);
    }


    @SafeVarargs
    public final GroupByStatement<T> groupBy(Function<T, Object>... functions) {
        return flux.groupBy(functions);
    }

    public OrderByStatement<T> orderBy(Function<T, Object> function) {
        return flux.orderBy(function);
    }

}
