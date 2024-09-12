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

    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder);

        user = new User.Builder()
                .setFirstName("Rethabile")
                .setLastName("Ntsekhe")
                .setEmail("rethabile@gmail.com")
                .setPassword("password")
                .setRole(Set.of("USER"))
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
                "rethabile@gmail.com",
                LocalDate.parse("1990-01-01"),
                Set.of("USER"),
                1234567890,  // **Change:** Ensure consistent phone number format
                "password123");

        User createdUser = userService.create(user);
        System.out.println(createdUser);

        assertNotNull(createdUser);
        assertEquals("Rethabile", createdUser.getFirstName());
        assertNotNull(userRepository.findByEmail("rethabile@gmail.com"));
    }

    @Test
    @Order(2)
    void testReadUser() {
        // Reuse user from the setUp method
        User createdUser = userService.create(user);
        User foundUser = userService.read(user.getUserID());
        System.out.println(foundUser);

        assertNotNull(foundUser);
        assertEquals("Rethabile", foundUser.getFirstName());
    }

    @Test
    @Order(3)
    void testUpdateUser() {
        // Modify user
        User createdUser = userService.create(user);
        createdUser = new User.Builder()
                .copy(createdUser)
                .setLastName("Southampton")  // Update the last name
                .build();

        User updatedUser = userService.update(createdUser);
        System.out.println(updatedUser);

        assertNotNull(updatedUser);
        assertEquals("Southampton", updatedUser.getLastName());
    }

    @Test
    @Order(5)
    void testFindAllUsers() {
        userService.create(user);
        List<User> users = userService.findAll();
        System.out.println("All Users :" + '\n' + users + '\n');

        assertNotNull(users);
    }

    @Test
    @Order(6)
    void testLoadUserByUsernameNotFound() {
        userService.create(user);
        assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("unknown@example.com"));
    }

    @Test
    @Order(7)
    void testFindByEmail() {
        userService.create(user);
        Optional<User> foundUser = userService.findByEmail("rethabile@gmail.com");
        System.out.println("Found By Email: " + '\n' + foundUser);

        assertTrue(foundUser.isPresent());
        assertEquals("Rethabile", foundUser.get().getFirstName());
    }

    // **Change:** Ensure consistent phone number usage and check for one user
    @Test
    @Order(11)
    void testFindByPhoneNumber() {
        // Create user with full phone number
        userService.create(user);

        // Find by full phone number
        List<User> users = userService.findByPhoneNumber(1234567890);
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
        userService.create(user);
        List<User> users = userService.findByFirstName("Rethabile");
        System.out.println("Found By First Name: " + users);

        assertFalse(users.isEmpty());
        assertEquals(1, users.size(), "There should be one user with the first name 'Rethabile'");
    }

    @Test
    @Order(9)
    void testFindByLastName() {
        userService.create(user);
        List<User> users = userService.findByLastName("Ntsekhe");
        System.out.println("Found By Last Name: " + users);

        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }

    @Test
    @Order(10)
    void testFindByBirthDate() {
        userService.create(user);
        List<User> users = userService.findByBirthDate(LocalDate.of(1990, 1, 1));
        System.out.println("Found By Birthday: " + users);

        assertFalse(users.isEmpty());
        assertEquals(1, users.size(), "There should be one user with this birth date");
    }

    @Test
    @Order(12)
    void testFindByRole() {
        userService.create(user);
        List<User> users = userService.findByRole("USER");
        System.out.println("Found By Roles: " + users);

        assertFalse(users.isEmpty());
        assertEquals(1, users.size(), "There should be one user with the role 'USER'");
    }
}