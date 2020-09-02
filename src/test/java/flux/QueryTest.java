package flux;

import flux.entities.Movie;
import flux.translators.Dialect;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static flux.utils.StringUtil.show;

public class QueryTest {

    static Flux<Movie> flux;

    @BeforeClass
    public static void init() {
        flux = new Flux<Movie>(Dialect.HIBERNATE, Movie::new) {
            @Override
            public List<Movie> toList() throws Exception {
                return null;
            }
        };
    }

    @Test
    public void test1() throws Exception {
        show(
                flux
                        .toQuery()
        );
    }

    @Test
    public void test2() throws Exception {
        show(
                flux
                        .select(Movie::getId, Movie::getTitle)
                        .toQuery()
        );
    }

    @Test
    public void test3() {
        show(
                flux
                        .select(Movie::getId, Movie::getTitle)
                        .where(Movie::getId)
                        .notBetween(10L, 20L)
                        .or(Movie::getTitle)
                        .equal("Matrix")
                        .toQuery()
        );
    }

    @Test
    public void test4() throws Exception {
        show(
                flux
                        .select(Movie::getId, Movie::getTitle)
                        .join(Movie::getGenre)
                        .toQuery()
        );
    }

    @Test
    public void test5() throws Exception {
        show(
                flux
                        .select(Movie::getId, Movie::getTitle)
                        .where(e -> e.getGenre().getName())
                        .in("Matrix", "Batman",1)
                        .toQuery()
        );
    }

    @Test
    public void test6() throws Exception {
        show(
                flux
                        .select(Movie::getId, Movie::getTitle)
                        .where(e -> e.getGenre().getName())
                        .in("Matrix", "Batman")
                        .orderBy(e -> e.getGenre().getId())
                        .asc()
                        .orderBy(Movie::getId)
                        .desc()
                        .toQuery()
        );
    }

    @Test
    public void test7() throws Exception {
        show(
                flux
                        .select(Movie::getId, Movie::getTitle)
                        .leftJoin(Movie::getTitle)
                        .where(e -> e.getGenre().getName())
                        .in("Matrix", "Batman")
                        .orderBy(e -> e.getGenre().getId())
                        .asc()
                        .orderBy(Movie::getId)
                        .desc()
                        .toQuery()
        );
    }


}