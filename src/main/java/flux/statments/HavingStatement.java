package flux.statments;

import flux.Flux;

import java.util.function.Supplier;

public class HavingStatement<T> extends Statement<T> {
    public HavingStatement(Supplier<T> supplier, Flux<T> flux) {
        super(supplier, flux);
    }
}
