package mk.ukim.finki.emt.moviecatalog.config;

import mk.ukim.finki.emt.moviecatalog.domain.models.Movie;
import mk.ukim.finki.emt.moviecatalog.domain.repository.MovieRepository;
import mk.ukim.finki.emt.moviecatalog.domain.valueObjects.Genre;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class DataInitializer {
    private final MovieRepository movieRepository;

    public DataInitializer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void initData() {
        Movie m1 = Movie.build("Titanic", 180, 5, Genre.THRILLER, Price.valueOf(Currency.MKD, 600));
        Movie m2 = Movie.build("Star Wars", 160, 4, Genre.SCI_FI, Price.valueOf(Currency.MKD, 400));
        if (movieRepository.findAll().isEmpty()) {
            movieRepository.saveAll(Arrays.asList(m1, m2));
        }
    }
}
