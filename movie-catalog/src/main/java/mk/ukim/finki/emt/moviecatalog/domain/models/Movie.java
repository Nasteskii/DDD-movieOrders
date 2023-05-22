package mk.ukim.finki.emt.moviecatalog.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.moviecatalog.domain.valueObjects.Genre;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;

import javax.persistence.*;

@Entity
@Table(name = "movie")
@Getter
public class Movie extends AbstractEntity<MovieId> {
    private String movieName;
    private int duration;
    private int rating;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private Price price;


    protected Movie() {
        super(MovieId.randomId(MovieId.class));
    }



    public static Movie build(String movieName, int duration, int rating, Genre genre, Price price) {
        Movie movie = new Movie();
        movie.movieName = movieName;
        movie.duration = duration;
        movie.rating = rating;
        movie.genre = genre;
        movie.price = price;
        return movie;
    }
}
