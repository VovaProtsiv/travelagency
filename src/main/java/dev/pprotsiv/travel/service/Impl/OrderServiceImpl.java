package dev.pprotsiv.travel.service.Impl;

import dev.pprotsiv.travel.exception.NullEntityReferenceException;
import dev.pprotsiv.travel.model.Order;
import dev.pprotsiv.travel.projection.OrderProjection;
import dev.pprotsiv.travel.repo.OrderRepository;
import dev.pprotsiv.travel.service.OrderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        if (order != null) {
            return orderRepository.save(order);
        }
        throw new NullEntityReferenceException("Order cannot be 'null'");
    }

    @Override
    public Order readById(long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order with id " + id + " not found"));
    }

    @Override
    public OrderProjection readProjectionById(long id) {
        return Optional.ofNullable(orderRepository.getProjectionById(id)).orElseThrow(
                () -> new EntityNotFoundException("Order with id " + id + " not found")
        );
    }

    @Override
    public Order update(Order order) {
        if (order != null) {
            readById(order.getId());
            return orderRepository.save(order);
        }
        throw new NullEntityReferenceException("Order cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        orderRepository.delete(readById(id));
    }

    @Override
    public List<OrderProjection> getProjectionsByUserId(long id) {
        List<OrderProjection> orders = orderRepository.getAllOrderProjectionByUserId(id);
        return orders.isEmpty() ? new ArrayList<>() : orders;
    }


}
