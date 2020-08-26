package fluent.statments;

import fluent.Flux;

import java.util.function.Supplier;

public class GroupByStatement<T> extends Statement<T> {
    public GroupByStatement(Supplier<T> supplier, Flux<T> flux) {
        super(supplier, flux);
    }
}
