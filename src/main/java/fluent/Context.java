package fluent;

import entities.Movie;
import fluent.translators.Dialect;
import repo.Repo;

public class Context extends Repo {



    public Flux<Movie> movies;

    public Context() {
        this.movies = new Flux<>(Dialect.SQL_SERVER, Movie::new);
    }
}

