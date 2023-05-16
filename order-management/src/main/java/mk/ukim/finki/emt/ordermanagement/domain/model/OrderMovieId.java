package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class OrderMovieId extends DomainObjectId {
    protected OrderMovieId(@NonNull Long uuid) {
        super(uuid);
    }
}
