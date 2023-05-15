package mk.ukim.finki.emt.moviecatalog.domain.models;

import mk.ukim.finki.emt.moviecatalog.domain.valueObjects.Genre;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie extends AbstractEntity<MovieId> {
    private String movieName;
    private int duration;
    private int rating;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private Price price;
}
