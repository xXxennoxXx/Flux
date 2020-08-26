package fluent.statments;

import fluent.Flux;

import java.util.function.Supplier;

public class JoinStatement<T> extends Statement<T> {
    public JoinStatement(Supplier<T> supplier, Flux<T> flux) {
        super(supplier, flux);
    }

    public Flux<T> join() {
        return flux;
    }

    public Flux<T> leftJoin() {
        return flux;
    }

    public Flux<T> rightJoin() {
        return flux;
    }

    public Flux<T> outerJoin() {
        return flux;
    }
}
