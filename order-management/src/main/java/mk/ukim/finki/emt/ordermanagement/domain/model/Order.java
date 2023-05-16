package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Movie;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.User;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.UserId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;
import mk.ukim.finki.emt.sharedkernel.domain.valueObjects.Quality;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends AbstractEntity<OrderId> {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderMovie> orderMovieSet;
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
    private UserId userId;
    @Column(name = "order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    protected Order() {
    }

    public Order(@NotNull UserId userId, @NotNull Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.currency = currency;
    }

    public Price totalPrice() {
        return orderMovieSet.stream().map(OrderMovie::subTotal)
                .reduce(new Price(currency, 0), Price::addPrice);
    }

    public OrderMovie addMovie(@NonNull Movie movie, Quality quality) {
        Objects.requireNonNull(movie, "movie must not be null");
        var item = new OrderMovie(movie.getMovieId(), movie.getPrice(), quality);
        orderMovieSet.add(item);
        return item;
    }

    public void removeMovie(@NonNull OrderMovieId orderMovieId) {
        Objects.requireNonNull(orderMovieId, "Order movie must not be null");
        orderMovieSet.removeIf(r -> r.getId().equals(orderMovieId));
    }
}
