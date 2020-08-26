package fluent;

import entities.Movie;
import fluent.evaluators.ClassToEvaluate;
import fluent.translators.Dialect;
import org.junit.Test;

import static utils.StringUtil.show;

public class ContextTest {

    @Test
    public void init() {
        Flux<Movie> flux = new Flux<>(Dialect.SQL_SERVER, Movie::new);

        String s = flux
                .where(e -> e.getGenre().getName())
                .in("Horror", "Comedy", "Romance")
                .orderBy(Movie::getTitle)
                .desc()
                .toString();

        flux
                .where(e -> e.getGenre().getName())
                .in("Horror", "Comedy", "Romance")
                .orderBy(Movie::getTitle)
                .desc()
                .toString();


        show(s);
    }

    @Test
    public void testSelect() {
        Flux<Movie> flux = new Flux<>(Dialect.SQL_SERVER, Movie::new);

        String s = flux
                .select(Movie::getTitle, Movie::getId)
                .where(e -> e.getGenre().getName())
                .in("'Horror'", "'Comedy'", "'Romance'")
                .orderBy(Movie::getTitle)
                .desc()
                .toString();

        show(s);
    }

    @Test
    public void testEnclosing() {

        ClassToEvaluate classToEvaluate = new ClassToEvaluate();
        classToEvaluate.setaDouble(1.3);

        Class<? extends Double> aClass = classToEvaluate.getaDouble().getClass();

    }
}