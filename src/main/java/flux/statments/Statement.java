package flux.statments;

import flux.Flux;
import flux.evaluators.FieldEvaluator;
import flux.fieldholders.FieldHolder;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Statement<T> {

    private final FieldEvaluator<T> fieldEvaluator;
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


    public FieldHolder getFieldHolder() {
        return fieldHolder;
    }

    public List<FieldHolder> getFields() {
        return fields;
    }
}
