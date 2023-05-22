package mk.ukim.finki.emt.moviecatalog.xport.rest;

import mk.ukim.finki.emt.moviecatalog.domain.models.Movie;
import mk.ukim.finki.emt.moviecatalog.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/movie")
public class MovieResource {

    private final MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAll() {
        return movieService.getAll();
    }
}
