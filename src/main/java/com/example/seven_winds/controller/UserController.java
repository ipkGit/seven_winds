package com.example.seven_winds.controller;


import com.example.seven_winds.model.User;
import com.example.seven_winds.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Users", description = "User methods")
@RestController
@RequestMapping("api/person")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Getting all users from DB")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Getting user by ID from DB")
    public User getAll(@PathVariable long id) {
        return userService.getUserByID(id);
    }

    @PostMapping
    @Operation(summary = "adding new user to DB")
    public User addUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete users by ID from DB")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
