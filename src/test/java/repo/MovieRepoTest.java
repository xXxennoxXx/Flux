package repo;

import entities.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static utils.StringUtil.show;

public class MovieRepoTest {

    private static MovieRepo movieRepo;

    @Before
    public  void init() {
        movieRepo = new MovieRepo();
    }

    @Test
    public void getMovies() {
        List<Movie> movies = movieRepo.getMovies();
        movies.forEach(e -> show(e.getTitle()));
        System.out.println("--------");
    }

    @Test
    public void getMoviesViaString() {
        List<Movie> from_movie = movieRepo.getMoviesViaString("from Movie");
        from_movie.forEach(e -> show(e.getTitle()));
        show("--------");

    }
}