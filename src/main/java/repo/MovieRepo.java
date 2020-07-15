package repo;

import entities.Movie;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;

public class MovieRepo extends Repo {


    public List<Movie> getMovies() {
        try (Session session = getSession()) {
            return session.createQuery("from entities.Movie m", Movie.class).getResultList();
        }
    }

    public List<Movie> getMoviesViaString(String query) {
        try (Session session = getSession()) {
            return session.createQuery(query, Movie.class).getResultList();
        }
    }
}
