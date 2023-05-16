package mk.ukim.finki.emt.moviecatalog.domain.repository;

import mk.ukim.finki.emt.moviecatalog.domain.models.Movie;
import mk.ukim.finki.emt.moviecatalog.domain.models.MovieId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, MovieId> {
}
