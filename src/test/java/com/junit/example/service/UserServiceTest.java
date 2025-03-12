package com.junit.example.service;

import com.junit.example.entity.User;
import com.junit.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    void testGetAllUsers() {
        List<User> users= Arrays.asList(new User("Haya", "haya@gmail.com"), new User("Raya","raya@gmail.com"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result=userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testAddUser(){
        User user=new User("Roshi", "roshi@gmail.com");
        when(userRepository.save(user)).thenReturn(user);
        User result=userService.addUser(user);
        assertNotNull(result);
        verify(userRepository,times(1)).save(user);
    }
}
