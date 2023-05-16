package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.MovieId;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.valueObjects.Quality;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_movie")
@Getter
public class OrderMovie extends AbstractEntity<OrderMovieId> {
    private Price price;
    private Quality quality;
    @AttributeOverride(name = "id", column = @Column(name = "movie_id", nullable = false))
    private MovieId movieId;

    public OrderMovie(@NonNull MovieId movieId, @NonNull Price price, @NonNull Quality quality) {
        super(DomainObjectId.randomId(OrderMovieId.class));
        this.movieId = movieId;
        this.price = price;
        this.quality = quality;
    }

    protected OrderMovie() {

    }

    public Price subTotal() {
        return price.getPriceByQuality(quality);
    }
}
