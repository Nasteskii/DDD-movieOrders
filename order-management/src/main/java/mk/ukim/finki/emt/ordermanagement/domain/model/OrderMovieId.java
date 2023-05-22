package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class OrderMovieId extends DomainObjectId {
    private OrderMovieId() {
        super(OrderMovieId.randomId(OrderMovieId.class).getId());
    }

    public OrderMovieId(@NonNull String uuid) {
        super(uuid);
    }
}
