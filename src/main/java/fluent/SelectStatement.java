package fluent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class SelectStatement<T> extends SqlStatement<T> {
    private List<Object> fields;

    public SelectStatement(Supplier<T> supplier, DbSet<T> dbSet, Function<T, Object>... functions) {
        super(supplier, dbSet);
        fields = new ArrayList<>();
        for (Function<T, Object> function : functions)
            fields.add(getAttributeName(function));
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
