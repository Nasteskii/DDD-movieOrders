package mk.ukim.finki.emt.moviecatalog.services;

import mk.ukim.finki.emt.moviecatalog.domain.models.Movie;
import mk.ukim.finki.emt.moviecatalog.domain.models.MovieId;
import mk.ukim.finki.emt.moviecatalog.services.form.MovieForm;
import mk.ukim.finki.emt.sharedkernel.domain.valueObjects.Quality;

import java.util.List;

public interface MovieService {
    Movie findById(MovieId movieId);
    Movie createMovie(MovieForm movieForm);
    Movie orderMovieCreated(MovieId movieId);
    Movie orderMovieRemoved(MovieId movieId);
    List<Movie> getAll();
}
