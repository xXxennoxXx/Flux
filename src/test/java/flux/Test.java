package flux;

import flux.entities.Movie;
import flux.evaluators.FieldEvaluator;
import flux.fieldholders.FieldHolder;
import flux.translators.Dialect;
import flux.translators.Translator;
import flux.translators.hibernate.HibernateTranslator;

import java.util.List;

import static flux.utils.StringUtil.show;

public class Test {

    @org.junit.Test
    public void test() {
        FieldEvaluator<Movie> movieFieldEvaluator = new FieldEvaluator<>(Movie::new);
        FieldHolder queryField = movieFieldEvaluator.findQueryField(Movie::getId);
        FieldHolder queryField1 = movieFieldEvaluator.findQueryField(Movie::getId);

        show(queryField.equals(queryField1));
    }

    @org.junit.Test
    public void test2() {
        FieldEvaluator<Movie> movieFieldEvaluator = new FieldEvaluator<>(Movie::new);
        FieldHolder queryField = movieFieldEvaluator.findQueryField(e -> e.getGenre().getId());
        FieldHolder queryField1 = movieFieldEvaluator.findQueryField(e -> e.getGenre().getId());

        show(queryField.equals(queryField1));
    }

    @org.junit.Test
    public void test3() throws Exception {
        Flux<Movie> flux = new Flux<Movie>(Dialect.HIBERNATE, Movie::new) {
            @Override
            public List<Movie> toList() throws Exception {
                return null;
            }
        };

        String s = flux
                .join(Movie::getGenre)
                .where(e -> e.getGenre().getId())
                .equal(1).toQuery();
        show(s);
    }
}
