package flux.statments;

import flux.Flux;

import java.util.function.Function;

public class JoinStatement<T> extends Statement<T> {

    public JoinStatement(Flux<T> flux) {
        super(flux);
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
