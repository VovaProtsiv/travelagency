package dev.pprotsiv.travel.controller;

import dev.pprotsiv.travel.dto.UserDto;
import dev.pprotsiv.travel.model.User;
import dev.pprotsiv.travel.projection.RoleProjection;
import dev.pprotsiv.travel.projection.UserProjection;
import dev.pprotsiv.travel.service.RoleService;
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
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllDtos());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleProjection>> getRoles(){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findAll());
    }
    @GetMapping("/roles/{userId}")
    public ResponseEntity<List<RoleProjection>> getRolesByUserId(@PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getDto(id));
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

