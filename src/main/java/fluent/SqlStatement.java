package fluent;

import fluent.evaluators.FieldEvaluator;

import java.util.function.Function;
import java.util.function.Supplier;

public class SqlStatement<T> {

    private FieldEvaluator<T> fieldEvaluator;
    protected final DbSet<T> dbSet;
    protected String fieldName;

    public SqlStatement(Supplier<T> supplier, DbSet<T> dbSet) {
        this.fieldEvaluator = new FieldEvaluator<>(supplier);
        this.dbSet = dbSet;
    }

    public String getAttributeName(Function<T, Object> function) {
        return fieldEvaluator.findQueryField(function);
    }

    DbSet<T> addToStatements() {
        dbSet.addStatement(this);
        return dbSet;
    }
}
