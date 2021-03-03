package dev.pprotsiv.travel.service;

import dev.pprotsiv.travel.dto.OrderDto;
import dev.pprotsiv.travel.model.Order;
import dev.pprotsiv.travel.projection.OrderProjection;

import java.util.List;

public interface OrderService {
    Order create(OrderDto dto);

    Order readById(long id);

    OrderProjection readProjectionById(long id);

    Order update(Order order);

    void delete(long id);

    List<OrderProjection> getProjectionsByUserId(long id);

}
