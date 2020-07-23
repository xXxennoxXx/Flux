package fluent;

import java.util.function.Supplier;


public class FromStatement<T> extends SqlStatement<T> {
    private Supplier<T> supplier;

    public FromStatement(Supplier<T> supplier, DbSet<T> dbSet) {
        super(supplier, dbSet);
        this.supplier = supplier;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb
                .append("FROM ")
                .append(supplier
                        .get()
                        .getClass()
                        .getSimpleName());
        return sb.toString();
    }
}
