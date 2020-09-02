package flux.checkers;

import flux.Flux;
import flux.HibernateDialectException;
import flux.fieldholders.FromFieldHolder;

import java.util.List;

public enum QueryHibernateCheckList {
    HAS_FROM_STATEMENT("Hibernate Query can't contain FROM statement") {
        @Override
        public <T> void check(Flux<T> flux) {
            List<FromFieldHolder> fromFields = flux.getFromFields();
            if (!fromFields.isEmpty())
                throwException();
        }
    };

    String name;

    QueryHibernateCheckList(String name) {
        this.name = name;
    }

    public abstract <T> void check(Flux<T> flux);

    void throwException() {
        throw new HibernateDialectException(name);
    }
}
