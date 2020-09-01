package flux.statments;

import flux.Flux;
import flux.OrderByType;

import java.util.function.Function;
import java.util.function.Supplier;

public class OrderByStatement<T> extends Statement<T> {

    private OrderByType type;

    public OrderByStatement(Supplier<T> supplier, Function<T, Object> function, Flux<T> flux) {
        super(supplier, flux);
        fieldHolder = getFieldHolder(function);
    }

    public Flux<T> asc() {
        type = OrderByType.ASC;
        return flux;
    }

    public Flux<T> ascending() {
        return asc();
    }

    public Flux<T> desc() {
        type = OrderByType.DESC;
        return flux;
    }

    public Flux<T> descending() {
        return desc();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ORDER BY ")
                .append(fieldHolder)
                .append(" ");
        switch (type) {
            case ASC:
                sb.append("ASC");
                break;
            case DESC:
                sb.append("DESC");
                break;
        }
        return sb.toString();
    }

    public OrderByType getType() {
        return type;
    }
}
