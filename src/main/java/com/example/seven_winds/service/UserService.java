package com.example.seven_winds.service;

import com.example.seven_winds.model.User;


import java.util.List;


public interface UserService {

    List<User> getAllUsers();

    User getUserByID(long id);

    User saveUser(User user);

    void deleteUser(long id);

}
