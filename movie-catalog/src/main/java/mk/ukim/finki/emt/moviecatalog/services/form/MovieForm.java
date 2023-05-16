package mk.ukim.finki.emt.moviecatalog.services.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import mk.ukim.finki.emt.moviecatalog.domain.valueObjects.Genre;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;

@Data
public class MovieForm {
    @NotNull
    private String movieName;
    @Min(1)
    private int duration = 1;
    @Min(0)
    @Max(5)
    private int rating = 0;
    @NotNull
    private Genre genre;
    @NotNull
    private Price price;


}
