package dev.pprotsiv.travel.controller;

import dev.pprotsiv.travel.dto.OrderDto;
import dev.pprotsiv.travel.model.Order;
import dev.pprotsiv.travel.model.State;
import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.projection.OrderProjection;
import dev.pprotsiv.travel.service.OrderService;
import dev.pprotsiv.travel.service.UserService;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("http://localhost:4200")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<OrderProjection>> getOrders(@PathVariable long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getProjectionsByUserId(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable long userId, @RequestBody OrderDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.create(dto));
    }

    @DeleteMapping("/remove/{order_id}")
    public void deleteRoom(@PathVariable long order_id) {

        Order order = orderService.readById(order_id);
        User user = order.getClient();
        user.removeOrder(order);
        userService.update(user);
        orderService.delete(order_id);
    }

    @PutMapping("/{order_id}/cancel")
    public ResponseEntity<Order> cancelBooking(@PathVariable long order_id) {
        Order order = orderService.readById(order_id);
        order.setState(State.CANCELED);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(order));
    }
}
