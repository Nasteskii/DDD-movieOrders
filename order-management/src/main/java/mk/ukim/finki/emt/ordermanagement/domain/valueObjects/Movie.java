package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;
import mk.ukim.finki.emt.sharedkernel.domain.valueObjects.Genre;

@Getter
public class Movie implements ValueObject {
    private final MovieId movieId;
    private final String name;
    private final int duration;
    private final int rating;
    private final Genre genre;
    private final Price price;

    protected Movie() {
        this.movieId = MovieId.randomId(MovieId.class);
        this.name = "";
        this.duration = 0;
        this.rating = 0;
        this.genre = Genre.ACTION;
        this.price = Price.valueOf(Currency.MKD, 0);
    }

    @JsonCreator
    public Movie(@JsonProperty("id") MovieId movieId,
                 @JsonProperty("movieName") String name,
                 @JsonProperty("duration") int duration,
                 @JsonProperty("rating") int rating,
                 @JsonProperty("genre") Genre genre,
                 @JsonProperty("price") Price price) {
        this.movieId = movieId;
        this.name = name;
        this.duration = duration;
        this.rating = rating;
        this.genre = genre;
        this.price = price;
    }
}
