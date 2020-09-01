package flux.statments;

import flux.Flux;

import java.util.function.Supplier;


public class FromStatement<T> extends Statement<T> {
    private Supplier<T> supplier;

    public FromStatement(Supplier<T> supplier, Flux<T> flux) {
        super(supplier, flux);
        this.supplier = supplier;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb
                .append("FROM ")
                .append(supplier
                        .get()
                        .getClass()
                        .getSimpleName());
        return sb.toString();
    }
}
