package mk.ukim.finki.emt.moviecatalog.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.moviecatalog.domain.models.MovieId;
import mk.ukim.finki.emt.moviecatalog.services.MovieService;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.OrderMovieCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieEventListener {

    private final MovieService movieService;

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "movieCatalog")
    public void consumeOrderMovieCreatedEvent(String jsonMessage) {
        try {
            OrderMovieCreated event = DomainEvent.fromJson(jsonMessage, OrderMovieCreated.class);
            movieService.orderMovieCreated(MovieId.of(event.getMovieId()));
        } catch (Exception e) {

        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "movieCatalog")
    public void consumeOrderMovieRemovedEvent(String jsonMessage) {
        try {
            OrderMovieCreated event = DomainEvent.fromJson(jsonMessage, OrderMovieCreated.class);
            movieService.orderMovieRemoved(MovieId.of(event.getMovieId()));
        } catch (Exception e) {

        }
    }
}
