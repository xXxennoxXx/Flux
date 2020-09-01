package flux.statments;

import flux.Flux;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

public class SelectStatement<T> extends Statement<T> {

    public SelectStatement(Supplier<T> supplier, Flux<T> flux, Function<T, Object>... functions) {
        super(supplier, flux);
        fields = new ArrayList<>();
        for (Function<T, Object> function : functions)
            fields.add(getFieldHolder(function));
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb
                .append("SELECT ");
        fields.forEach(e -> sb
                .append(e.toString())
                .append(", "));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
