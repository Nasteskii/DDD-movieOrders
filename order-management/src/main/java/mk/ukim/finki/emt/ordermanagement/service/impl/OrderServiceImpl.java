package mk.ukim.finki.emt.ordermanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderIdNotExistsException;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderMovieIdNotExistsException;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderMovieId;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.MovieId;
import mk.ukim.finki.emt.ordermanagement.service.OrderService;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderMovieForm;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.OrderMovieCreated;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.OrderMovieRemoved;
import mk.ukim.finki.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;

    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "order must not be null");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getOrderMovieSet().forEach(movie -> domainEventPublisher.publish(new OrderMovieCreated(movie.getMovieId().getId())));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void addMovie(OrderId orderId, OrderMovieForm orderMovieForm) throws OrderIdNotExistsException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistsException::new);
        order.addMovie(orderMovieForm.getMovie(), orderMovieForm.getQuality());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderMovieCreated(orderMovieForm.getMovie().getMovieId().getId()));
    }

    @Override
    public void deleteMovie(OrderId orderId, OrderMovieId orderMovieId) throws OrderIdNotExistsException, OrderMovieIdNotExistsException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistsException::new);
        order.removeMovie(orderMovieId);
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderMovieRemoved(orderMovieId.getId()));
    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(orderForm.getUser().getUserId(), orderForm.getCurrency());
        orderForm.getMovies().forEach(r -> order.addMovie(r.getMovie(), r.getQuality()));
        return order;
    }
}
