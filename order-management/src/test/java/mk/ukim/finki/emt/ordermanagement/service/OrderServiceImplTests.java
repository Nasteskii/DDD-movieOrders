package mk.ukim.finki.emt.ordermanagement.service;

import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderIdNotExistsException;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderMovieIdNotExistsException;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Movie;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.MovieId;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.User;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.UserId;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderMovieForm;
import mk.ukim.finki.emt.ordermanagement.xport.client.MovieClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;
import mk.ukim.finki.emt.sharedkernel.domain.valueObjects.Genre;
import mk.ukim.finki.emt.sharedkernel.domain.valueObjects.Quality;
import mk.ukim.finki.emt.sharedkernel.domain.valueObjects.QualitySize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTests {
    @Autowired
    private OrderService orderService;

    @Autowired
    private MovieClient movieClient;

    private static Movie newMovie(String movieName, int duration, int rating,  Genre genre, Price price) {
        Movie movie = new Movie(MovieId.randomId(MovieId.class), movieName, duration, rating, genre, price);
        return movie;
    }

    @Test
    public void testgetMovie() {

        OrderMovieForm orderMovieForm = new OrderMovieForm();
        orderMovieForm.setMovie(newMovie("Titanic", 180, 4, Genre.DRAMA, Price.valueOf(Currency.MKD, 600)));
        orderMovieForm.setQuality(new Quality(QualitySize.FHD));

        OrderMovieForm orderMovieForm1 = new OrderMovieForm();
        orderMovieForm1.setMovie(newMovie("Iron Man",150, 5, Genre.ACTION, Price.valueOf(Currency.MKD, 900)));
        orderMovieForm1.setQuality(new Quality(QualitySize.UHD));

        OrderForm orderForm = new OrderForm();
        orderForm.setUser(new User(UserId.randomId(UserId.class), "Borce", "Borce", Price.valueOf(Currency.MKD, 2000)));
        orderForm.setCurrency(Currency.MKD);
        orderForm.setMovies(Arrays.asList(orderMovieForm, orderMovieForm1));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistsException::new);
        Assertions.assertEquals(newOrder.totalPrice(), Price.valueOf(Currency.MKD, 2100));
    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Movie> movieList = movieClient.findAll();
        Movie m1 = movieList.get(0);
        Movie m2 = movieList.get(1);

        OrderMovieForm om1 = new OrderMovieForm();
        om1.setMovie(m1);
        om1.setQuality(new Quality(QualitySize.HD));

        OrderMovieForm om2 = new OrderMovieForm();
        om2.setMovie(m2);
        om2.setQuality(new Quality(QualitySize.FHD));

        OrderForm orderForm = new OrderForm();
        orderForm.setUser(new User(UserId.randomId(UserId.class), "Borce", "Borce", Price.valueOf(Currency.MKD, 2000)));
        orderForm.setCurrency(Currency.MKD);
        orderForm.setMovies(Arrays.asList(om1, om2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderMovieIdNotExistsException::new);

        Price outMoney = m1.getPrice().getPriceByQuality(om1.getQuality()).addPrice(m2.getPrice().getPriceByQuality(om2.getQuality()));
        Assertions.assertEquals(newOrder.totalPrice(), outMoney);

    }
}
