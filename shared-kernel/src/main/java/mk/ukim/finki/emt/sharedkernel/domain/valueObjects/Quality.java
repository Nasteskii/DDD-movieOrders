package mk.ukim.finki.emt.sharedkernel.domain.valueObjects;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Quality implements ValueObject {
    @Enumerated(value = EnumType.STRING)
    private final QualitySize qualitySize;

    protected Quality() {
        this.qualitySize = null;
    }

    public Quality(@NonNull QualitySize qualitySize) {
        this.qualitySize = qualitySize;
    }

    public QualitySize getQuality() {
        return qualitySize;
    }

    public double getQualityAddition() {
        switch (Objects.requireNonNull(qualitySize)){
            case HD -> {
                return 1;
            }
            case FHD, QHD -> {
                return 1.25;
            }
            case UHD -> {
                return 1.5;
            }
        }
        return 0;
    }
}
