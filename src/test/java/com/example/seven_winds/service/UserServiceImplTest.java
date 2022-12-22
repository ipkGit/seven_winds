package com.example.seven_winds.service;

import com.example.seven_winds.model.User;
import com.example.seven_winds.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    UserRepository userRepository = mock(UserRepository.class);

    UserService userService;

    List<User> userList;

    @BeforeEach
    public void setUp() {
        userList = getUserList();
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void test_GetAllUsers_success() {
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userService.getAllUsers();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(userList.get(1), result.get(1));
    }

    @Test
    void test_GetUserByID_success() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userList.get(0)));
        User result = userService.getUserByID(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userList.get(0), result);
        Assertions.assertEquals("Igor", result.getName());
    }

    @Test()
    void test_GetUserByID_shouldThrowNotFoundExceptions() {
        Mockito.when(userRepository.findById(5L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> userService.getUserByID(5L));
    }

    @Test
    void saveUser() {
        User user = new User(4L, "New", "User", "serviceMan", "8949 54 54 555", "email@test.ru");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User result = userService.saveUser(user);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("New", result.getName());
        Assertions.assertEquals(user, result);
    }


    private List<User> getUserList() {

        User RECORD_1 = new User(1L,
                "Igor", "Kripak", "Petrovich",
                "+7 888 9999 888", "test@email.com");
        User RECORD_2 = new User(2L,
                "Lena", "Egorova", "Olegovna",
                "8 (111)2222333", "lena@email.ur");
        User RECORD_3 = new User(3L,
                "Leonel", "Messi", "Winner",
                "+7 800 805 35 35", "goat@yandex.com");

        return new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

    }

}