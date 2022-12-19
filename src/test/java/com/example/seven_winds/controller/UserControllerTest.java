package com.example.seven_winds.controller;


import com.example.seven_winds.model.User;
import com.example.seven_winds.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    User RECORD_1 = new User(1L,
            "Igor", "Kripak", "Petrovich",
            "+7 888 9999 888", "test@email.com");
    User RECORD_2 = new User(2L,
            "Lena", "Egorova", "Olegovna",
            "8 (111)2222333", "lena@email.ur");
    User RECORD_3 = new User(3L,
            "Leonel", "Messi", "Winner",
            "+7 800 805 35 35", "goat@yandex.com");


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsers_success() throws Exception {
        List<User> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(userService.getAllUsers()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/person")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(3)))
                        .andExpect(jsonPath("$[2].name", is("Leonel")));
    }

    @Test
    public void getUserById_success() throws Exception {
        List<User> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(userService.getUserByID(1L)).thenReturn(records.get(0));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Igor")));
    }

}