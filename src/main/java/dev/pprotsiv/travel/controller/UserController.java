package dev.pprotsiv.travel.controller;

import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.readById(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.create(user);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable long id) {
        userService.delete(id);
    }

    @PutMapping("/users/{id}")
    public User editUser(@RequestBody User user, @PathVariable long id) {
        return userService.update(user);
    }
}

