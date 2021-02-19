package dev.pprotsiv.travel.controller;

import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.projection.OrderProjection;
import dev.pprotsiv.travel.projection.UserProjection;
import dev.pprotsiv.travel.service.OrderService;
import dev.pprotsiv.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;

    }

    @GetMapping
    public ResponseEntity<List<UserProjection>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllProjections());
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderProjection>> getOrders(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getProjectionsByUserId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProjection> getUserById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getProjection(id));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.create(user));
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userService.delete(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> editUser(@RequestBody User user, @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
    }
}

