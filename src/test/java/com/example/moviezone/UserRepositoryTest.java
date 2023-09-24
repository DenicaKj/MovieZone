package com.example.moviezone;

import com.example.moviezone.model.User;
import com.example.moviezone.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        // Create a User entity
        User user = new User();
        user.setUsername("testuser");
        user.setAddress("test@example.com");

        // Save the user to the database
        User savedUser = userRepository.save(user);

        // Assert that the user was saved and has an ID
        Assertions.assertNotNull(savedUser.getId_user());
    }

    @Test
    public void testFindUserByUsername() {
        // Save a user to the database
        User user = new User();
        user.setUsername("testuser");
        user.setAddress("test@example.com");
        userRepository.save(user);

        // Retrieve the user by username
        Optional<User> retrievedUser = Optional.ofNullable(userRepository.findByUsername("testuser"));

        // Assert that the retrieved user matches the saved user
        assertTrue(retrievedUser.isPresent());
        assertEquals("test@example.com", retrievedUser.get().getAddress());
    }

    @Test
    public void testDeleteUser() {
        // Save a user to the database
        User user = new User();
        user.setUsername("testuser");
        user.setAddress("test@example.com");
        userRepository.save(user);

        // Delete the user from the database
        userRepository.delete(user);

        // Assert that the user is no longer in the database
        assertNull(userRepository.findByUsername("testuser"));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testUserCount() {
        // Save three users to the database
        User user1 = new User();
        user1.setUsername("user1");
        user1.setAddress("user1@example.com");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setAddress("user2@example.com");
        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("user3");
        user3.setAddress("user3@example.com");
        userRepository.save(user3);

        // Assert that the count of users in the database is 3
        assertEquals(3, userRepository.count());
    }

    @Test
    public void testUsernameNotNull() {
        // Attempt to save a user with a null username
        User user = new User();
        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void testUsernameUnique() {
        // Save a user with a unique username
        User user1 = new User();
        user1.setUsername("uniqueUsername");
        userRepository.save(user1);

        // Attempt to save another user with the same username (non-unique)
        User user2 = new User();
        user2.setUsername("uniqueUsername");
        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> userRepository.save(user2));
    }
}