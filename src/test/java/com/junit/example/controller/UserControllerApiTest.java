package com.junit.example.controller;

import com.junit.example.entity.User;
import com.junit.example.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class UserControllerApiTest {

    @LocalServerPort
    int port;

    @MockBean
    UserService userService;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testGetAllUsers(){
        List<User> users= Arrays.asList(new User("jack", "jack.gmail.com"),new User("Jill", "jill@gmail.com"));
        when(userService.getAllUsers()).thenReturn(users);

        List<User> result= testRestTemplate.getForObject("http://localhost:" + port + "/api/users/all", List.class);
        Assertions.assertEquals(2,result.size());
    }
}
