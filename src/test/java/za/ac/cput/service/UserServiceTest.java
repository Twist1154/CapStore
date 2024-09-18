package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import za.ac.cput.OnlineClothingStoreApp;
import za.ac.cput.domain.User;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OnlineClothingStoreApp.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserService userservice;

    private User user;

    @BeforeEach
    void setUp() {
        userservice = new UserService(userRepository, passwordEncoder);

        user = new User.Builder()
                .setFirstName("Rethabile")
                .setLastName("Ntsekhe")
                .setEmail("rethabile1154n@gmail.com")
                .setPassword("password")
                .setRole(Set.of("USER","ADMIN"))
                .setBirthDate(LocalDate.of(1990, 1, 1))
                .setPhoneNumber(1234567890)
                .build();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();  // Ensure DB is cleaned up after each test
    }

    @Test
    @Order(1)
    void testCreateUser() {
        // Create user using factory
        user = UserFactory.createUser(
                "avatar.jpg",
                "Rethabile",
                "Ntsekhe",
                "rethabile1154n@gmail.com",
                LocalDate.parse("1990-01-01"),
                Set.of("USER","ADMIN"),
                1234567890,
                "password123");

        User createdUser = userservice.create(user);
        System.out.println(createdUser);

        assertNotNull(createdUser);
        assertEquals("Rethabile", createdUser.getFirstName());
        assertNotNull(userRepository.findByEmail("rethabile@gmail.com"));
    }

    @Test
    @Order(2)
    void testReadUser() {
        // Reuse user from the setUp method
        User createdUser = userservice.create(user);
        User foundUser = userservice.read(user.getUserID());
        System.out.println(foundUser);

        assertNotNull(foundUser);
        assertEquals("Rethabile", foundUser.getFirstName());
    }

    @Test
    @Order(3)
    void testUpdateUser() {
        // Modify user
        User createdUser = userservice.create(user);
        createdUser = new User.Builder()
                .copy(createdUser)
                .setLastName("Zulu")  // Update the last name
                .build();

        User updatedUser = userservice.update(createdUser);
        System.out.println(updatedUser);

        assertNotNull(updatedUser);
        assertEquals("Zulu", updatedUser.getLastName());
    }

    @Test
    @Order(5)
    void testFindAllUsers() {
        userservice.create(user);
        List<User> users = userservice.findAll();
        System.out.println("All Users :" + '\n' + users + '\n');

        assertNotNull(users);
    }

    @Test
    @Order(6)
    void testLoadUserByUsernameNotFound() {
        userservice.create(user);
        assertThrows(UsernameNotFoundException.class,
                () -> userservice.loadUserByUsername("unknown@example.com"));
    }

    @Test
    @Order(7)
    void testFindByEmail() {
        userservice.create(user);
        Optional<User> foundUser = userservice.findByEmail("rethabile1154n@@gmail.com");
        System.out.println("Found By Email: " + '\n' + foundUser);

        assertTrue(foundUser.isPresent());
        assertEquals("Rethabile", foundUser.get().getFirstName());
    }

    // **Change:** Ensure consistent phone number usage and check for one user
    @Test
    @Order(11)
    void testFindByPhoneNumber() {
        // Create user with full phone number
        userservice.create(user);

        // Find by full phone number
        List<User> users = userservice.findByPhoneNumber(1234567890);
        System.out.println("Found By Phone Number: " + users);

        // **Change:** Add size check to ensure only one user is returned
        assertFalse(users.isEmpty(), "User list should not be empty");
        assertEquals(1, users.size(), "Only one user should be found");
        assertEquals("Rethabile", users.get(0).getFirstName(), "The user's first name should be 'Rethabile'");
    }

    // **Change:** Adjust the other methods to ensure similar consistency and validations
    @Test
    @Order(8)
    void testFindByFirstName() {
        userservice.create(user);
        List<User> users = userservice.findByFirstName("Rethabile");
        System.out.println("Found By First Name: " + users);

        assertFalse(users.isEmpty());
        assertEquals(1, users.size(), "There should be one user with the first name 'Rethabile'");
    }

    @Test
    @Order(9)
    void testFindByLastName() {
        userservice.create(user);
        List<User> users = userservice.findByLastName("Ntsekhe");
        System.out.println("Found By Last Name: " + users);

        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }

    @Test
    @Order(10)
    void testFindByBirthDate() {
        userservice.create(user);
        List<User> users = userservice.findByBirthDate(LocalDate.of(1990, 1, 1));
        System.out.println("Found By Birthday: " + users);

        assertFalse(users.isEmpty());
        assertEquals(1, users.size(), "There should be one user with this birth date");
    }

    @Test
    @Order(12)
    void testFindByRole() {
        userservice.create(user);
        List<User> users = userservice.findByRole("USER");
        System.out.println("Found By Roles: " + users);

        assertFalse(users.isEmpty());
        assertEquals(1, users.size(), "There should be one user with the role 'USER'");
    }
}