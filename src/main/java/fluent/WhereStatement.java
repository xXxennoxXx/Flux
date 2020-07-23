package fluent;

import java.util.function.Function;
import java.util.function.Supplier;

public class WhereStatement<T> extends SqlStatement<T> {

    private SqlStatement<T> nextStatement = null;
    private WhereCondition whereCondition;

    public WhereStatement(Supplier<T> supplier, Function<T, Object> function, DbSet<T> dbSet) {
        super(supplier, dbSet);
        fieldName = getAttributeName(function);
    }

    public DbSet<T> equal(Object value) {
        whereCondition = new WhereCondition(WhereCondition.Type.EQUAL, value);
        return addToStatements();
    }

    public DbSet<T> lessThen(Object value) {
        whereCondition = new WhereCondition(WhereCondition.Type.LESS_THEN, value);
        return addToStatements();
    }

    public DbSet<T> greaterThen(Object value) {
        whereCondition = new WhereCondition(WhereCondition.Type.GREATER_THEN, value);
        return addToStatements();
    }

    public DbSet<T> notEqual(Object value) {
        whereCondition = new WhereCondition(WhereCondition.Type.NOT_EQUAL, value);
        return addToStatements();
    }

    public DbSet<T> in(Object... values) {
        whereCondition = new WhereCondition(WhereCondition.Type.IN, values);
        return addToStatements();
    }

    public DbSet<T> between(Object low, Object top) {
        whereCondition = new WhereCondition(WhereCondition.Type.BETWEEN, low, top);
        return addToStatements();
    }

    public DbSet<T> notBetween(Object low, Object top) {
        whereCondition = new WhereCondition(WhereCondition.Type.NOT_BETWEEN, low, top);
        return addToStatements();
    }

    @Override
    public String toString() {
        return "WHERE " + fieldName + " " + whereCondition.toString();
    }
}
