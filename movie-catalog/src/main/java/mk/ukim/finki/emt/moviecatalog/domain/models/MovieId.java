package mk.ukim.finki.emt.moviecatalog.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class MovieId extends DomainObjectId {
    private MovieId() {
        super(MovieId.randomId(MovieId.class).getId());
    }

    public MovieId(@NonNull String uuid) {
        super(uuid);
    }

    public static MovieId of(String uuid) {
        MovieId mid = new MovieId(uuid);
        return mid;
    }

}
