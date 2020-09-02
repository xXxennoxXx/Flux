package flux.statments;

import flux.Flux;
import flux.evaluators.FieldEvaluator;
import flux.fieldholders.FieldHolder;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Statement<T> extends Condition<T> {


    public Statement(Flux<T> flux) {
        super(flux);
    }

    public List<T> toList() throws Exception {
        return flux.toList();
    }

    public String toQuery()  {
        return flux.toQuery();
    }


}
