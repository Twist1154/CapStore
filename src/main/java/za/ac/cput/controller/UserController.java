package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Users;
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
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = userService.create(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user if found, or 404 status if not
     */
    @GetMapping("/read/{id}")
    public ResponseEntity<Users> readUser(@PathVariable Long id) {
        Users user = userService.read(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    /**
     * Updates an existing users.
     *
     * @param users the updated users details
     * @return the updated users if the update is successful, or 404 status if the users does not exist
     */
    @PutMapping("/update")
    public ResponseEntity<Users> updateUser(@RequestBody Users users) {
        Users updatedUser = userService.update(users);
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
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users by their email.
     *
     * @param email the email to search by
     * @return the user if found, or 404 status if not
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Users> findByEmail(@PathVariable String email) {
        Optional<Users> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Finds users by their first name.
     *
     * @param firstName the first name to search by
     * @return a list of users with the specified first name
     */
    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<Users>> findByFirstName(@PathVariable String firstName) {
        List<Users> users = userService.findByFirstName(firstName);
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users by their last name.
     *
     * @param lastName the last name to search by
     * @return a list of users with the specified last name
     */
    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<Users>> findByLastName(@PathVariable String lastName) {
        List<Users> users = userService.findByLastName(lastName);
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users by their birthdate.
     *
     * @param birthDate the birthdate to search by
     * @return a list of users with the specified birthdate
     */
    @GetMapping("/birthdate/{birthDate}")
    public ResponseEntity<List<Users>> findByBirthDate(@PathVariable LocalDate birthDate) {
        List<Users> users = userService.findByBirthDate(birthDate);
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users by their phone number.
     *
     * @param phoneNumber the phone number to search by
     * @return a list of users with the specified phone number
     */
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<List<Users>> findByPhoneNumber(@PathVariable Integer phoneNumber) {
        List<Users> users = userService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(users);
    }

    /**
     * Finds users by their role.
     *
     * @param role the role to search by
     * @return a list of users with the specified role
     */
    @GetMapping("/role/{role}")
    public ResponseEntity<List<Users>> findByRole(@PathVariable String role) {
        List<Users> users = userService.findByRole(role);
        return ResponseEntity.ok(users);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        Set<Users> user = userService.verifyUser(email, password);
        if (user.isEmpty()) {
            // Failed login
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } else {
            // Successful login
            return ResponseEntity.ok("Login successful");
        }
    }
}
