package mk.ukim.finki.emt.sharedkernel.domain.base;

import lombok.Getter;
import lombok.NonNull;
import com.fasterxml.jackson.annotation.JsonCreator;


import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@MappedSuperclass
@Embeddable
@Getter
public class DomainObjectId implements Serializable {
    private Long id;

    public DomainObjectId() {

    }

    @JsonCreator
    protected DomainObjectId(@NonNull Long uuid) {
        this.id = Objects.requireNonNull(uuid, "uuid must not be null");
    }

    @NonNull
    public static <ID extends DomainObjectId> ID randomId(@NonNull Class<ID> idClass) {
        Objects.requireNonNull(idClass, "idClass must not be null");
        try {
            return idClass.getConstructor(Long.class).newInstance(Long.parseLong(UUID.randomUUID().toString()));
        } catch (Exception ex) {
            throw new RuntimeException("Could not create new instance of " + idClass, ex);
        }
    }

}
