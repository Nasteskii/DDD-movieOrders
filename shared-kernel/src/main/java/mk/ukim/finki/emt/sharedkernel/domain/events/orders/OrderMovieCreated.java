package mk.ukim.finki.emt.sharedkernel.domain.events.orders;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class OrderMovieCreated extends DomainEvent {
    private String movieId;
    private int quantity;

    public OrderMovieCreated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
    }

    public OrderMovieCreated(String movieId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.movieId = movieId;
        this.quantity = quantity;
    }

}
