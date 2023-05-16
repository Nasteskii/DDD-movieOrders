package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;

@Getter
public class Movie implements ValueObject {
    private final MovieId movieId;
    private final String name;
    private final Price price;

    protected Movie() {
        this.movieId = MovieId.randomId(MovieId.class);
        this.name = "";
        this.price = Price.valueOf(Currency.MKD, 0);
    }

    @JsonCreator
    public Movie(MovieId movieId, String name, Price price) {
        this.movieId = movieId;
        this.name = name;
        this.price = price;
    }
}
