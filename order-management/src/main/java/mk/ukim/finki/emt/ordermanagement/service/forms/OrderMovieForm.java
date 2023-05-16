package mk.ukim.finki.emt.ordermanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Movie;
import mk.ukim.finki.emt.sharedkernel.domain.valueObjects.Quality;
import mk.ukim.finki.emt.sharedkernel.domain.valueObjects.QualitySize;

import javax.validation.constraints.NotNull;

@Data
public class OrderMovieForm {
    @NotNull
    private Movie movie;

    private Quality quality = new Quality(QualitySize.HD);
}
