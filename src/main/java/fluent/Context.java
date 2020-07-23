package fluent;

import entities.Movie;
import repo.Repo;

import java.util.function.Supplier;

public class Context extends Repo {



    public DbSet<Movie> movies;

    public Context() {
        this.movies = new DbSet<>(Movie::new);
    }
}
