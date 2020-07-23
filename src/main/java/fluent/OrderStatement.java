package fluent;

import fluent.DbSet;
import fluent.SqlStatement;

import java.util.function.Function;
import java.util.function.Supplier;

public class OrderStatement<T> extends SqlStatement<T> {
    enum Type {ASC, DESC}

    private Type type;

    public OrderStatement(Supplier<T> supplier, Function<T, Object> function, DbSet<T> dbSet) {
        super(supplier, dbSet);
        fieldName = getAttributeName(function);
    }

    public DbSet asc() {
        type = Type.ASC;
        return addToStatements();
    }

    public DbSet ascending() {
        return asc();
    }

    public DbSet desc() {
        type = Type.DESC;
        return addToStatements();
    }

    public DbSet descending() {
        return desc();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ORDER BY ")
                .append(fieldName)
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
