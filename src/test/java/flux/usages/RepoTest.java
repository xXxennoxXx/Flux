package flux.usages;

import flux.entities.Genre;
import flux.entities.Movie;
import flux.translators.Dialect;
import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static flux.utils.StringUtil.show;
import static org.junit.jupiter.api.Assertions.*;

public class RepoTest {

    static Repo<Movie> movieRepo;

    @BeforeClass
    public static void init() throws Exception {

        movieRepo = new Repo<>(Dialect.HIBERNATE, Movie::new);
        Genre romance = new Genre(),
                horror = new Genre();
        romance.setName("Romance");
        horror.setName("Horror");

        Movie movie1 = new Movie(),
                movie2 = new Movie(),
                movie3 = new Movie();

        movie1.setGenre(romance);
        romance.getMovies().add(movie1);

        movie2.setGenre(romance);
        romance.getMovies().add(movie2);

        movie3.setGenre(horror);
        horror.getMovies().add(movie3);

        movie1.setTitle("Titanic");
        movie2.setTitle("Dirty dancing");
        movie3.setTitle("The Ring");

        try (Session session = movieRepo.getSession()) {
            session.beginTransaction();
            session.save(romance);
            session.save(horror);
            session.save(movie1);
            session.save(movie2);
            session.save(movie3);
            session.getTransaction().commit();
        }


    }

    @Test
    public void testSelect() throws Exception {
        List<Movie> movies = movieRepo.toList();

        Assertions.assertNotNull(movies);
        Assertions.assertEquals(movies.size(), 3);
    }

    @Test
    public void testWhere() throws Exception {
        List<Movie> movies = movieRepo
                .where(Movie::getTitle)
                .equal("\'Titanic\'")
                .toList();

        Assertions.assertNotNull(movies);
        Assertions.assertEquals(movies.size(), 1);
        Assertions.assertTrue(movies.get(0).getTitle().equals("Titanic"));
    }

}