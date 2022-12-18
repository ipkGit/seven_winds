package com.example.seven_winds.controller;

import com.example.seven_winds.model.User;
import com.example.seven_winds.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "Users", description = "User methods")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/person")
public class UserController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Getting all users from DB")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Getting user by ID from DB")
    public User getUserById(@PathVariable long id) {
        try {
            return userService.getUserByID(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "adding new user to DB")
    public User addUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete users by ID from DB")
    public void deleteUser(@PathVariable long id) {
        try {
            userService.deleteUser(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
