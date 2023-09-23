package com.example.moviezone;

import com.example.moviezone.model.*;
import com.example.moviezone.repository.CustomerRepository;
import com.example.moviezone.repository.UserRepository;
import com.example.moviezone.repository.WorkerRepository;
import com.example.moviezone.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc()
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private WorkerRepository workerRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Test
    public void testFindAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        userService.findAllUsers();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Integer userId = 1;
        User mockUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        User result = userService.findById(userId);

        verify(userRepository, times(1)).findById(userId);
        assertEquals(mockUser, result);
    }

    @Test
    public void testFindByUsername() {
        String username = "testUser";
        User mockUser = new User();
        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        User result = userService.findByUsername(username);

        verify(userRepository, times(1)).findByUsername(username);
        assertEquals(mockUser, result);
    }

    @Test
    public void testRegister() {
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe";
        String email = "johndoe@example.com";
        String number = "1234567890";
        String password = "password";
        Role role = Role.ROLE_USER;

        when(userRepository.save(any(Customer.class))).thenReturn(new Customer());

        userService.register(firstName, lastName, username, email, number, password, role);

        verify(userRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testRegisterWorker() {
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe";
        String email = "johndoe@example.com";
        String number = "1234567890";
        String password = "password";
        String position = "Worker";
        String workHoursFrom = "9:00 AM";
        String workHoursTo = "5:00 PM";
        Cinema cinema = new Cinema();

        when(userRepository.save(any(Worker.class))).thenReturn(new Worker());

        userService.registerWorker(firstName, lastName, username, email, number, password, position, workHoursFrom, workHoursTo, cinema);

        verify(userRepository, times(1)).save(any(Worker.class));
    }

    @Test
    public void testLogin() {
        String username = "testUser";
        String password = "password";
        User mockUser = new User();
        when(userRepository.findAllByUsernameAndPassword(username, password)).thenReturn(Collections.singletonList(mockUser));

        User result = userService.login(username, password);

        verify(userRepository, times(1)).findAllByUsernameAndPassword(username, password);
        assertEquals(mockUser, result);
    }
}
