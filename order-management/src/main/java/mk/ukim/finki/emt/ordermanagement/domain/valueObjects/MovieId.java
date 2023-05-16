package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class MovieId extends DomainObjectId {
    protected MovieId() {
        super(MovieId.randomId(MovieId.class).getId());
    }

    public MovieId(Long uuid) {
        super(uuid);
    }

}
