package mk.ukim.finki.emt.sharedkernel.domain.valueObjects;

import javax.persistence.Embeddable;

@Embeddable
public enum Genre {
    COMEDY,
    ACTION,
    DRAMA,
    THRILLER,
    HORROR,
    ROMANCE,
    SCI_FI
}
