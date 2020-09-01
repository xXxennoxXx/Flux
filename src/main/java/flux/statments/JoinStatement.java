package flux.statments;

import flux.Flux;
import flux.JoinType;
import flux.fieldholders.JoinFieldHolder;

import java.util.function.Function;
import java.util.function.Supplier;

public class JoinStatement<T> extends Statement<T> {

    private final JoinType joinType;
    private final JoinFieldHolder joinFieldHolder;

    public JoinStatement(Supplier<T> supplier, Flux<T> flux, Function<T, Object> function, JoinType joinType) {
        super(supplier, flux);
        this.joinType = joinType;
        joinFieldHolder = new JoinFieldHolder(getFieldHolder(function), joinType);
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public JoinFieldHolder getJoinFieldHolder() {
        return joinFieldHolder;
    }
}
