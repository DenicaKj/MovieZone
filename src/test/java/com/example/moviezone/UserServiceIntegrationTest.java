package com.example.moviezone;

import com.example.moviezone.model.*;
import com.example.moviezone.model.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.moviezone.repository.CustomerRepository;
import com.example.moviezone.repository.UserRepository;
import com.example.moviezone.service.UserService;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = MovieZoneApplication.class)
@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
public class UserServiceIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Test
    public void testFindAllUsers() {
        // Prepare test data
        User user1 = new User("user1", "password1", "User 1", "user1@example.com", "12345", "ROLE_USER");
        User user2 = new User("user2", "password2", "User 2", "user2@example.com", "67890", "ROLE_USER");
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();

        // Call the service method
        Iterable<User> users = userService.findAllUsers();

        // Assert
        assertNotNull(users);
        assertEquals(943, ((List<User>) users).size());
    }

    @Test
    public void testRegisterCustomer() {
        // Prepare test data
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe1";
        String email = "johndoe@example.com";
        String number = "123456789";
        String password = "password";
        Role role = Role.ROLE_USER;

        // Call the service method
        userService.register(firstName, lastName, username, email, number, password, role);

        // Verify that the customer is saved in the database
        Customer savedCustomer = customerRepository.getByUsername(username);
        assertNotNull(savedCustomer);
        assertEquals(username, savedCustomer.getUsername());
    }

    @Test
    public void testRegisterWorker() {
        // Prepare test data
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe1";
        String email = "johndoe@example.com";
        String number = "123456789";
        String password = "password";
        String position = "Worker";
        String works_hours_from= "12";
        String works_hours_to="8";
        Cinema cinema= new Cinema();
        cinema.setId_cinema(1);

        // Call the service method
        userService.registerWorker(firstName, lastName, username, email, number, password, position,works_hours_from,works_hours_to,cinema);

        // Verify that the worker is saved in the database
        User savedWorker = userRepository.findByUsername(username);
        assertNotNull(savedWorker);
        assertEquals(username, savedWorker.getUsername());
        assertTrue(savedWorker instanceof Worker);
    }

    @Test
    public void testLogin() {
        // Prepare test data
        String username = "test";
        String password = "testPassword";
        User user = new User(username, passwordEncoder.encode(password), "User 1", "user1@example.com", "12345", "ROLE_USER");
        entityManager.persist(user);
        entityManager.flush();

        // Call the service method
        User loggedInUser = userService.login(username, password);

        // Assert
        assertNotNull(loggedInUser);
        assertEquals(username, loggedInUser.getUsername());
    }

    @Test
    public void testLoginFailure() {
        // Attempt to login with invalid credentials
        String invalidUsername = "invalidUser";
        String invalidPassword = "invalidPassword";

        assertThrows(UserNotFoundException.class, () -> userService.login(invalidUsername, invalidPassword));
    }

}
