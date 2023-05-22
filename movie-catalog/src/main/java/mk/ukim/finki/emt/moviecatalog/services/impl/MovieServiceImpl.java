package mk.ukim.finki.emt.moviecatalog.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.moviecatalog.domain.exceptions.MovieNotFoundException;
import mk.ukim.finki.emt.moviecatalog.domain.models.Movie;
import mk.ukim.finki.emt.moviecatalog.domain.models.MovieId;
import mk.ukim.finki.emt.moviecatalog.domain.repository.MovieRepository;
import mk.ukim.finki.emt.moviecatalog.services.MovieService;
import mk.ukim.finki.emt.moviecatalog.services.form.MovieForm;
import mk.ukim.finki.emt.sharedkernel.domain.valueObjects.Quality;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public Movie findById(MovieId movieId) {
        return movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
    }

    @Override
    public Movie createMovie(MovieForm movieForm) {
        Movie movie = Movie.build(movieForm.getMovieName(), movieForm.getDuration(),
                movieForm.getRating(), movieForm.getGenre(), movieForm.getPrice());
        return movieRepository.save(movie);
    }

    @Override
    public Movie orderMovieCreated(MovieId movieId) {
        Movie m = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        movieRepository.saveAndFlush(m);
        return m;

    }

    @Override
    public Movie orderMovieRemoved(MovieId movieId) {
        Movie m = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        movieRepository.saveAndFlush(m);
        return m;
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }
}
