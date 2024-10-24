package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.User;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UserFactory.
 * This class verifies the correctness of the User creation and validation logic.
 */
class UserFactoryTest {

    private User user;
    private Set<String> authorities;

    @BeforeEach
    void setup() {
        // Set up a sample set of roles (authorities)
        authorities = new HashSet<>();
        authorities.add("Admin");
        authorities.add("User");

        // Set up a sample User object using the factory method
        user = UserFactory.createUser(
                "avatar.jpg",
                "John",
                "Doe",
                "johndoe",
                "johndoe@example.com",
                LocalDate.parse("1990-01-01"),
                authorities,
                "123456789",
                "password123");
    }

    @Test
    void testCreateUser() {
        // Verify that the User object is not null
        assertNotNull(user);

        // Additional assertions to verify field values
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUsername());  // Verify username
        assertEquals("johndoe@example.com", user.getEmail());
        assertEquals(LocalDate.parse("1990-01-01"), user.getBirthDate());
        assertEquals("123456789", user.getPhoneNumber());  // Phone number is now a String
        assertEquals("password123", user.getPassword());
        assertEquals(authorities, user.getAuthorities());

        // Print the created User object to the terminal
        System.out.println("Created User: " + user);
    }

    @Test
    void testCreateUser_WithNullFirstName_ThrowsIllegalArgumentException() {
        // Try to create a User object with null first name
        assertThrows(IllegalArgumentException.class,
                () -> UserFactory.createUser(
                        "avatar.jpg",
                        null,
                        "Doe",
                        "johndoe",
                        "johndoe@example.com",
                        LocalDate.parse("1990-01-01"),
                        authorities,
                        "123456789",
                        "password123"));

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating User with null first name");
    }

    @Test
    void testCreateUser_WithNullLastName_ThrowsIllegalArgumentException() {
        // Try to create a User object with null last name
        assertThrows(IllegalArgumentException.class,
                () -> UserFactory.createUser(
                        "avatar.jpg",
                        "John",
                        null,
                        "johndoe",
                        "johndoe@example.com",
                        LocalDate.parse("1990-01-01"),
                        authorities,
                        "123456789",
                        "password123"));

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating User with null last name");
    }

    @Test
    void testCreateUser_WithNullEmail_ThrowsIllegalArgumentException() {
        // Try to create a User object with null email
        assertThrows(IllegalArgumentException.class,
                () -> UserFactory.createUser(
                        "avatar.jpg",
                        "John",
                        "Doe",
                        "johndoe",
                        null,
                        LocalDate.parse("1990-01-01"),
                        authorities,
                        "123456789",
                        "password123"));

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating User with null email");
    }

    @Test
    void testCreateUser_WithNullPassword_ThrowsIllegalArgumentException() {
        // Try to create a User object with null password
        assertThrows(IllegalArgumentException.class,
                () -> UserFactory.createUser(
                        "avatar.jpg",
                        "John",
                        "Doe",
                        "johndoe",
                        "johndoe@example.com",
                        LocalDate.parse("1990-01-01"),
                        authorities,
                        "123456789",
                        null));

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating User with null password");
    }

    @Test
    void testCreateUserForSignIn() {
        // Create a User object for sign-in using the factory method
        User signInUser = UserFactory.createUserForSignIn("johndoe@example.com", "password123");

        // Verify that the User object is not null
        assertNotNull(signInUser);

        // Verify the email and password
        assertEquals("johndoe@example.com", signInUser.getEmail());
        assertEquals("password123", signInUser.getPassword());

        // Print the created User object to the terminal
        System.out.println("Created User for sign-in: " + signInUser);
    }

    @Test
    void testCreateUserForSignIn_WithNullEmail_ThrowsIllegalArgumentException() {
        // Try to create a User object for sign-in with null email
        assertThrows(IllegalArgumentException.class,
                () -> UserFactory.createUserForSignIn(null, "password123"));

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating User for sign-in with null email");
    }

    @Test
    void testCreateUserForSignIn_WithNullPassword_ThrowsIllegalArgumentException() {
        // Try to create a User object for sign-in with null password
        assertThrows(IllegalArgumentException.class,
                () -> UserFactory.createUserForSignIn("johndoe@example.com", null));

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating User for sign-in with null password");
    }
}
