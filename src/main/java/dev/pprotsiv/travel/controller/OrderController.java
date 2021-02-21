package dev.pprotsiv.travel.controller;

import dev.pprotsiv.travel.model.Order;
import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.projection.OrderProjection;
import dev.pprotsiv.travel.service.OrderService;
import dev.pprotsiv.travel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
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


    @DeleteMapping("/remove/{order_id}")
    public void deleteRoom(@PathVariable long order_id) {

        Order order = orderService.readById(order_id);
        User user = order.getClient();
        user.removeOrder(order);
        userService.update(user);
        orderService.delete(order_id);
    }

    @PutMapping("/edit/{order_id}")
    public Order editRoom(@PathVariable long order_id, @RequestBody Order order) {

        return order;
    }
}
