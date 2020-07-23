package fluent;

import entities.Movie;
import org.junit.Test;

import static utils.StringUtil.show;

public class ContextTest {

    @Test
    public void init() {
        DbSet<Movie> dbSet = new DbSet<>(Movie::new);

        String s = dbSet
                .where(e -> e.getGenre().getName())
                .in("Horror", "Comedy", "Romance")
                .orderBy(Movie::getTitle)
                .desc()
                .toString();

        show(s);
    }

    @Test
    public void testSelect() {
        DbSet<Movie> dbSet = new DbSet<>(Movie::new);

        String s = dbSet
                .select(Movie::getTitle, Movie::getId)
                .where(e -> e.getGenre().getName())
                .in("'Horror'", "'Comedy'", "'Romance'")
                .orderBy(Movie::getTitle)
                .desc()
                .toString();

        show(s);
    }

}