package dev.pprotsiv.travel.controller;

import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id= " + id + " not found"));
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/users/{id}")
    public User editUser(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        return userRepository.save(user.
                orElseThrow(() -> new EntityNotFoundException("User with id= " + id + " not found")));
    }
}

