package fluent.statments;

import fluent.Flux;
import fluent.OrderByType;

import java.util.function.Function;
import java.util.function.Supplier;

public class OrderByStatement<T> extends Statement<T> {

    private OrderByType type;

    public OrderByStatement(Supplier<T> supplier, Function<T, Object> function, Flux<T> flux) {
        super(supplier, flux);
        fieldHolder = getFieldHolder(function);
    }

    public Flux asc() {
        type = OrderByType.ASC;
        return addToStatements();
    }

    public Flux ascending() {
        return asc();
    }

    public Flux desc() {
        type = OrderByType.DESC;
        return addToStatements();
    }

    public Flux descending() {
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
}
