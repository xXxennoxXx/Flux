package fluent.statments;

import fluent.Flux;
import fluent.evaluators.FieldEvaluator;
import fluent.fieldholders.FieldHolder;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Statement<T> {

    private FieldEvaluator<T> fieldEvaluator;
    protected final Flux<T> flux;
    protected FieldHolder fieldHolder;
    protected List<FieldHolder> fields;

    public Statement(Supplier<T> supplier, Flux<T> flux) {
        this.fieldEvaluator = new FieldEvaluator<>(supplier);
        this.flux = flux;
    }

    public FieldHolder getFieldHolder(Function<T, Object> function) {
        return fieldEvaluator.findQueryField(function);
    }

    public Flux<T> addToStatements() {
        flux.addStatement(this);
        return flux;
    }
}
