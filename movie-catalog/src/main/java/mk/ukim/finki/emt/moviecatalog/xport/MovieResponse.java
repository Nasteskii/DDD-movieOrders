package mk.ukim.finki.emt.moviecatalog.xport;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.moviecatalog.domain.models.Movie;
import mk.ukim.finki.emt.moviecatalog.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class MovieResponse
{
    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAll() {
        return this.movieService.getAll();
    }
}
