package fluent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class DbSet<T> {

    private final Supplier<T> supplier;
    private final List<SqlStatement<T>> statements;

    public DbSet(Supplier<T> supplier) {
        this.supplier = supplier;
        statements = new ArrayList<>();
    }

    void addStatement(SqlStatement<T> statement) {
        statements.add(statement);
    }

    //    TODO O Co chodzi
    @SafeVarargs
    public final DbSet<T> select(Function<T, Object>... functions) {
        SqlStatement<T> statement = new SelectStatement<>(supplier, this, functions);
        addStatement(statement);
        SqlStatement<T> statement2 = new FromStatement<>(supplier, this);
        addStatement(statement2);
        return this;
    }

    public WhereStatement<T> where(Function<T, Object> function) {
        return new WhereStatement<>(supplier, function, this);
    }

    public OrderStatement<T> orderBy(Function<T, Object> function) {
        return new OrderStatement<>(supplier, function, this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (SqlStatement<T> s : statements) {
            sb.append("\n")
                    .append(s.toString());
        }
        return sb.toString();
    }
}
