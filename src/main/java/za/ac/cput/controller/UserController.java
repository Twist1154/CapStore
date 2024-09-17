package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.User;
import za.ac.cput.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Controller for managing users in the system.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user.
     *
     * @param user the user object to be created
     * @return the created user
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.create(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user if found, or 404 status if not
     */
    @GetMapping("/read/{id}")
    public ResponseEntity<User> readUser(@PathVariable Long id) {
        User user = userService.read(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    /**
     * Updates an existing user.
     *
     * @param user the updated user details
     * @return the updated user if the update is successful, or 404 status if the user does not exist
     */
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     * @return a status indicating success or failure
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a list of all users
     */
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users by their email.
     *
     * @param email the email to search by
     * @return the user if found, or 404 status if not
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Finds users by their first name.
     *
     * @param firstName the first name to search by
     * @return a list of users with the specified first name
     */
    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<User>> findByFirstName(@PathVariable String firstName) {
        List<User> users = userService.findByFirstName(firstName);
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users by their last name.
     *
     * @param lastName the last name to search by
     * @return a list of users with the specified last name
     */
    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<User>> findByLastName(@PathVariable String lastName) {
        List<User> users = userService.findByLastName(lastName);
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users by their birthdate.
     *
     * @param birthDate the birthdate to search by
     * @return a list of users with the specified birthdate
     */
    @GetMapping("/birthdate/{birthDate}")
    public ResponseEntity<List<User>> findByBirthDate(@PathVariable LocalDate birthDate) {
        List<User> users = userService.findByBirthDate(birthDate);
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users by their phone number.
     *
     * @param phoneNumber the phone number to search by
     * @return a list of users with the specified phone number
     */
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<List<User>> findByPhoneNumber(@PathVariable Integer phoneNumber) {
        List<User> users = userService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users by their role.
     *
     * @param role the role to search by
     * @return a list of users with the specified role
     */
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> findByRole(@PathVariable String role) {
        List<User> users = userService.findByRole(role);
        return ResponseEntity.ok(users);
    }


        @PostMapping("/login")
        public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
            Set<User> user = userService.verifyUser(email, password);
            if (user.isEmpty()) {
                // Failed login
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            } else {
                // Successful login
                return ResponseEntity.ok("Login successful");
            }
        }
}
